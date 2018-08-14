package com.sinohealth.dscp.test;

import com.sinohealth.dscp.DrugApplication;
import com.sinohealth.dscp.constants.enumClass.SqlType;
import com.sinohealth.dscp.model.Drug;
import com.sinohealth.dscp.service.DrugServiceV1;
import org.apache.http.util.Asserts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DrugApplication.class})
@ActiveProfiles(profiles = "test")
public class DrugTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DrugServiceV1 drugServiceV1;

    @Test
    public void testAddDrug() {
        Drug drug = new Drug();
        drug.setCreateTime(new Date());
        drug.setCreateUser("lj");
        drug.setRoleName("role" + new Random(100).nextInt());
        drug.setRoleStatus(Drug.StatusEnum.Status1.getCode());
        drug.setUpdateTime(new Date());
        drug.setUpdateUser("lj");
        drugServiceV1.addDrug(drug);
    }

    @Test
    public void testMiltInsert() {
        //先新增数据
        long startTime = 0L;
        final int addNumber = 50000;
        List<Drug> drugs = addObj(addNumber, true, SqlType.joinSql.getCode());
        if (!drugs.isEmpty()) {
            startTime = System.currentTimeMillis();
            drugServiceV1.insertDrugList(drugs);
            logger.info("新增使用{}类型，用时：{}", SqlType.joinSql.getDescribe(), (System.currentTimeMillis() - startTime) / 1000);
        }
    }

    @Test
    public void testDeleteDrug() {
        List<Drug> drugs = drugServiceV1.getDrugs();
        int id = 0;
        if (drugs.size() > 0) {
            id = drugs.get(0).getId();
        }
        boolean flag = drugServiceV1.deleteDrug(id) == 1;
        logger.info("刪除数据编号：{},{}!", id, flag ? "成功" : "失败");
    }

    @Test
    public void testGetDrugById() {
        final Integer id = 1;
        Drug drug = drugServiceV1.getMyDrugById(id);
        Asserts.check(drug != null, "drug不能为空！");
        System.out.print(drug.toString());
    }

    @Test
    public void testGetDrugs() {
        List<Drug> drugs = drugServiceV1.getDrugs();
        Asserts.check(drugs != null || drugs.size() == 0, "drug不能为空！");
        drugs.forEach(drug -> System.out.print(drug.toString() + "\n"));
    }

    @Test
    public void updateDrug() {
        final Integer id = 1;
        Drug drug = drugServiceV1.getMyDrugById(id);
        if (drug == null) {
            throw new NullPointerException("drug为空！");
        }
        drug.setUpdateTime(new Date());
        drug.setCreateTime(new Date());
        boolean flag = drugServiceV1.updateDrug(drug) == 1;
        Asserts.check(flag, "更新drug出错！");
    }

    private List<Drug> addObj(int addNumber, boolean isDeleteOld, int insertType) {
        if (addNumber < 0 && addNumber == 0) {
            return Collections.emptyList();
        }
        if (isDeleteOld) {
            Integer counts = drugServiceV1.getCountForDrug();
            if (counts > 0) {
                long startTime = System.currentTimeMillis();
                boolean flag = drugServiceV1.truncateTable() == 1;
                logger.info("刪除数据{}条成功！，共耗时：{}", counts, (startTime - System.currentTimeMillis()) / 1000);
            }
        }
        Drug drug = null;
        List<Drug> drugs = new ArrayList<>(addNumber + 1);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < addNumber; i++) {
            drug = new Drug();
            drug.setCreateTime(new Date());
            drug.setUpdateTime(new Date());
            drug.setUpdateUser("lj" + i + 1);
            drug.setRoleName("role" + i);
            drug.setRoleStatus(i / 2 == 0 ? 1 : 0);
            drug.setCreateUser("lj" + i + 1);
            drugs.add(drug);
        }
        logger.info("准备数据{}条，用时：{}", addNumber, (System.currentTimeMillis() - startTime) / 1000);
        if (insertType == 1) {
            drugs.forEach(item -> drugServiceV1.addDrug(item));
            return Collections.emptyList();
        }
        if (insertType == 2) {
            return drugs;
        }
        return Collections.emptyList();
    }

}

