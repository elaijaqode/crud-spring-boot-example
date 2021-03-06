package com.easysoftwarespringbootapp.easysoftwarespringbootapp.service;


import com.easysoftwarespringbootapp.easysoftwarespringbootapp.entities.GeoIP;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;


import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;


@Service
public class DBDemoGeoIPLocationService {

    private DatabaseReader dbReader;

    public DBDemoGeoIPLocationService() throws IOException {
        File database = new File("your-path-to-db-file");
        dbReader = new DatabaseReader.Builder(database).build();
    }



    public GeoIP getLocation(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);
        String cityName = response.getCity().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();
        return new GeoIP(ip, cityName, latitude, longitude);
    }
}
