package com.test;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.util.HashMap;
import java.util.Map;

public class DistanceByLongitudeAndLatitude {
    public static void main(String[] args) {

        /*System.out.println(isDistance(116.4712313, 39.86231223, 112.34123123, 39.9443242, 1));
        System.out.println(isDistance(116.4712313, 39.86231223, 112.34123123, 39.9443242, 12));
        System.out.println(isDistance(116.47, 39.86, 112.34, 39.94, 12));
        System.out.println(GetDistance(116.4712313,39.86231223, 112.34123123,39.9443242));
        testDistanceAPI();*/
        System.out.println(findNeighPositioin(116.4712313, 39.86231223,12));
        returnLLSquarePoint(116.4712313,39.86231223,12);
    }

    private static Map<String,Double> findNeighPositioin(double longitude, double latitude,double dis) {
        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude * Math.PI / 180));
        dlng = dlng * 180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat * 180 / Math.PI;
        double minlat = latitude - dlat;
        double maxlat = latitude + dlat;
        double minlng = longitude - dlng;
        double maxlng = longitude + dlng;
        String sql = "from 表名 where longitude >= ? and longitude<=? and latitude >=? and latitude <=?";
        Object[] values = {minlng, maxlng, minlat, maxlat};
        Map<String, Double> result = new HashMap<>();
        result.put("minlat", minlat);
        result.put("maxlat", maxlat);
        result.put("minlng", minlng);
        result.put("maxlng", maxlng);
        return result;
    }

    /**
     * 返回两个经纬度之间距离是否超过指定distance距离
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @param distance
     * @return
     */
    private static boolean isDistance(double longitude1, double latitude1, double longitude2, double latitude2, long distance) {
        Map<String, Double> map = findNeighPositioin(longitude1, latitude1,distance);
        double minlat = map.get("minlat");
        double maxlat = map.get("maxlat");
        double minlng = map.get("minlng");
        double maxlng = map.get("maxlng");
        if (minlat < latitude2 && latitude2 < maxlat &&
                minlng < longitude2 && longitude2 < maxlng) {
            return true;
        }
        return false;
    }



    /**
     * 默认地球半径
     */
    private static double EARTH_RADIUS = 6371000;//赤道半径(单位m)
    private static double EARTH_RADIUS1 = 6371;//赤道半径(单位km)

    /**
     * 转化为弧度(rad)
     * */
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    /**
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位m
     * */
    public static double GetDistance(double lon1,double lat1,double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }




    public static void testDistanceAPI(){

//        GlobalCoordinates source = new GlobalCoordinates(29.490295, 106.486654);
//        GlobalCoordinates target = new GlobalCoordinates(29.615467, 106.581515);

        GlobalCoordinates source = new GlobalCoordinates(116.4712313, 39.86231223);

        GlobalCoordinates target = new GlobalCoordinates(112.34123123, 39.9443242);

        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);

        System.out.println("Sphere坐标系计算结果："+meter1 + "米");
        System.out.println("WGS84坐标系计算结果："+meter2 + "米");
    }

    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){

        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();
    }


    /**
     * 根据圆心及半径计算外切圆四个顶点坐标
     * https://blog.csdn.net/YimBa/article/details/83307861
     * @param longitude
     * @param latitude
     * @param distance
     * @return
     */
    public static Map<String, double[]> returnLLSquarePoint(double longitude, double latitude, double distance) {
        Map<String, double[]> squareMap = new HashMap<>();
        //计算经度弧度,从弧度转换为角度
        double dLongitude = 2 * (Math.asin(Math.sin(distance / (2 * EARTH_RADIUS1)) / Math.cos(Math.toRadians(latitude))));
        dLongitude = Math.toDegrees(dLongitude);
        //计算纬度角度
        double dLatitude = distance / EARTH_RADIUS1;
        dLatitude = Math.toDegrees(dLatitude);
        //正方形
        double[] leftTopPoint = {latitude + dLatitude, longitude - dLongitude};
        double[] rightTopPoint = {latitude + dLatitude, longitude + dLongitude};
        double[] leftBottomPoint = {latitude - dLatitude, longitude - dLongitude};
        double[] rightBottomPoint = {latitude - dLatitude, longitude + dLongitude};
        squareMap.put("leftTopPoint", leftTopPoint);
        squareMap.put("rightTopPoint", rightTopPoint);
        squareMap.put("leftBottomPoint", leftBottomPoint);
        squareMap.put("rightBottomPoint", rightBottomPoint);
        System.out.println("latitude: min-> " + (latitude - dLatitude) + ", max-> " + (latitude + dLatitude)
        +"; longitude: min-> "+ (longitude - dLongitude) + ", max-> "+ (longitude + dLongitude));
        return squareMap;
    }


}
