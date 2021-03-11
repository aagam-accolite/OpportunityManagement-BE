package com.accolite.opportunitymanagement.service.Impl;

import com.accolite.opportunitymanagement.service.TrendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("TrendsService")
@Transactional
public class TrendsServiceImpl implements TrendsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, String>> getCountLoc() {
        List<Map<String, String>> list = new ArrayList<>();
        String SQL = "SELECT location FROM opportunity ";
        return complexDataHandler(SQL);
    }

    @Override
    public List<Map<String, String>> getCountSkills() {
        List<Map<String, String>> list = new ArrayList<>();
        String SQL = "SELECT skills FROM opportunity ";
        return complexDataHandler(SQL);
    }

    @Override
    public List<Map<String, String>> getCountDemand() {
        List<Map<String, String>> list = new ArrayList<>();
        String SQL = "SELECT demand, count(*) FROM opportunity GROUP BY demand;";
        return dataHandler(SQL);
    }

    @Override
    public List<Map<String, String>> getCountMinExp() {
        List<Map<String, String>> list = new ArrayList<>();
        String SQL = "SELECT minExperience, count(*) FROM opportunity GROUP BY minExperience;";
        return dataHandler(SQL);
    }

    @Override
    public List<Map<String, String>> getCountDescription() {
        List<Map<String, String>> list = new ArrayList<>();
        String SQL = "SELECT description, count(*) FROM opportunity GROUP BY description;";
        return dataHandler(SQL);
    }

    private List<Map<String, String>> complexDataHandler(String SQL)
    {
        List<Map<String, String>> list = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        jdbcTemplate.query(SQL,resultSet -> {
            do {
                String str = resultSet.getString(1);
               // System.out.println(str);
                String arr[] = str.split(",", 0);
                for (int i = 0; i < arr.length; i++) {
                    if (hashMap.get(arr[i].toLowerCase()) == null) {
                        hashMap.put(arr[i].toLowerCase(), 1);
                    } else {
                        Integer c = hashMap.get(arr[i].toLowerCase());
                        c++;
                        hashMap.put(arr[i].toLowerCase(), c);
                    }
                }
            } while (resultSet.next());
        });
        //System.out.println(hashMap);
        Iterator<Map.Entry<String, Integer>> itr = hashMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            Map<String, String> temp = new HashMap<>();
            temp.put("name", entry.getKey());
            temp.put("value", entry.getValue().toString());
            list.add(temp);
        }
        return list;
    }
    private List<Map<String, String>> dataHandler(String SQL){
        List<Map<String, String>> list = new ArrayList<>();
        jdbcTemplate.query(SQL,resultSet -> {
            do {
                Map<String,String> temp = new HashMap<>();
                temp.put("name",resultSet.getString(1));
                temp.put("value", resultSet.getString(2));
                list.add(temp);
            }while (resultSet.next());
        });
        return list;
    }
}


