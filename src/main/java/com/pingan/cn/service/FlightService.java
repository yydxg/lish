package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.controller.vo.FlightVo;
import com.pingan.cn.dao.CommonDao;
import com.pingan.cn.dao.FlightDao;
import com.pingan.cn.entity.Common;
import com.pingan.cn.entity.Flight;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("flightService")
public class FlightService {
    @Autowired
    private FlightDao flightDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd hh:mm");

    public Flight saveAction(Flight flight){
        Flight save = null;
        try {
            return flightDao.save(flight);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Flight flight){
        if(flight == null || StrUtil.isBlank(flight.getId())){
            return  false;
        }
        Optional<Flight> byId = flightDao.findById(flight.getId());
        if (byId.isPresent()){
            Flight oldCommon = flightDao.findById(flight.getId()).get();
            BeanUtils.copyProperties(flight,oldCommon, SpringUtil.getNullPropertyNames(flight));
            try {
                flightDao.saveAndFlush(oldCommon);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Flight> findAll(){
        return flightDao.findAll();
    }

    public Flight findById(String id){
        return flightDao.findById(id).get();
    }


    public List<Flight> findByConditions(FlightVo flightVo){
        String departureCityName = flightVo.getDepartureCityName();
        String arrivalCityName = flightVo.getArrivalCityName();
        String marketAirlineName = flightVo.getMarketAirlineName();
        String flightNo = flightVo.getFlightNo();
        String aircraftSize = flightVo.getAircraftSize();
        String mealType = flightVo.getMealType();
        Double arrivalPunctuality = flightVo.getArrivalPunctuality();
        Double minAdultPrice = flightVo.getMinAdultPrice();
        Double maxAdultPrice = flightVo.getMaxAdultPrice();
        String startDate = flightVo.getStartDate();
        String endDate = flightVo.getEndDate();
        System.out.println(flightVo.toString());
        try {
            List<Flight> returnFlights = new ArrayList<>();
            List<Flight> flights = flightDao.findByConditions(departureCityName,arrivalCityName,marketAirlineName,flightNo,
                    aircraftSize,mealType,arrivalPunctuality,minAdultPrice,maxAdultPrice);
            System.out.println(flights.size());
            for (Flight flight:flights) {
                String dateStr = flight.getDepartureDateTime();
                Date date = simpleDateFormat2.parse(dateStr);
                Date start = simpleDateFormat.parse(startDate);
                Date end = simpleDateFormat.parse(endDate);
                if (start.before(date) && end.after(date)){
                    returnFlights.add(flight);
                }
            }
            return returnFlights;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
