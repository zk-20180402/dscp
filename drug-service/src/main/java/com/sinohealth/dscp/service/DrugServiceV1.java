package com.sinohealth.dscp.service;

import com.sinohealth.dscp.mapper.DrugMapper;
import com.sinohealth.dscp.model.Drug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class DrugServiceV1{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DrugMapper drugMapper;

    @Autowired
    public DrugServiceV1(DrugMapper drugMapper) {
        this.drugMapper = drugMapper;
    }

    /**
     * 获取我的频道
     */
    /*public list<drug> getmydrugbyname(string name) {
        return drugrepository.finddrug(name);
    }*/

    /**
     * 获取我的频道
     */
    public Drug getMyDrugById(Integer id) {
        return drugMapper.selectByPrimaryKey(id);
        //return drugMapper.selectDrugById(id);
        //return DrugRepository.findOne(id);
    }

    /**
     * 获取我的频道
     */
    public List<Drug> getDrugs() {
        return drugMapper.selectDrugs();
        //return drugMapper.selectDrugById(id);
        //return DrugRepository.findOne(id);
    }

    /**
     * 新增频道
     * @param drug
     */
    @Transactional
    public void addDrug(Drug drug) {
        //DrugRepository.save(Drug);
        //drugMapper.insert(Drug);
        //使用注解方式
        drugMapper.insertDrug(drug.getCreateTime(), drug.getCreateUser(),drug.getRoleName(),drug.getRoleStatus(),drug.getUpdateTime(),drug.getUpdateUser());
    }

    /*public DrugRepository getDrugRepository() {
        return DrugRepository;
    }*/

    /**
     * 修改频道
     * 注意：删除了修改频道需要添加事务，新增和查询不需要
     * @return
     */
    @Transactional
    public Integer updateDrug(Drug drug) {
        //return drugMapper.updateDrug(create_time, update_time, id);
        return drugMapper.updateByPrimaryKey(drug);
    }

    @Transactional
    public Integer deleteDrug(Integer id){
        //return drugMapper.deleteDrugById(id);
        return drugMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public Integer truncateTable(){
        return drugMapper.truncateTable();
    }

    public Integer getCountForDrug(){
        return drugMapper.getCountForDrug();
    }

    @Transactional
    public Integer insertDrugList(List<Drug> drugs){
        return drugMapper.insertDrugList(drugs);
    }
}
