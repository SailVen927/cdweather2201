package service;

import DAL.DBhelper;
import entity.percent;
import entity.weather;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class service {
    public List<weather> getWeather(int year) throws SQLException {
        String sql="select * from weather where data like '"+year+"%'";
        ResultSet rs= DBhelper.executeQuery(sql);
        List<weather> weathers=new ArrayList<weather>();
        while (rs.next()){
            weather w=new weather();
            w.setWdate(rs.getString("data"));
            w.setWeather(rs.getString("weather"));
            w.setTemperature(rs.getString("temperature"));
            w.setWind(rs.getString("wind"));
            weathers.add(w);
        }
        return weathers;
    }

    public List<percent> countPercent() throws SQLException{
        String sql="select weather from weather where data like '2012%'";
        ResultSet rs= DBhelper.executeQuery(sql);
        List<percent> percents = new ArrayList<percent>();
        String[] weathers = null;
        for(int i=0;rs.next();i+=2){
            //String[] tiniweathers = rs.getString("weather").split("/");
            weathers[i]=rs.getString("weather").split("/")[0];
            System.out.println(weathers[i]);
            weathers[i+1]=rs.getString("weather").split("/")[1];
            System.out.println(weathers[i+1]);
        }
        for (int j=0;weathers[j]!=null;j++){
            for (int z=0;z<percents.size();z++){
                if(percents.get(z).weather==weathers[j]){
                    percents.get(z).setPercent(percents.get(z).getPercent()+1);
                    break;
                }
                percent p = new percent();
                p.setWeather(weathers[j]);
                p.setPercent(1);
                percents.add(p);
            }
        }

        return percents;

    }
}
