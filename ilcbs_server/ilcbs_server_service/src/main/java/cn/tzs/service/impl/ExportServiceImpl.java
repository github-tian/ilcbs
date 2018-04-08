package cn.tzs.service.impl;

import cn.tzs.dao.ContractDao;
import cn.tzs.dao.ContractProductDao;
import cn.tzs.dao.ExportDao;
import cn.tzs.domain.*;
import cn.tzs.service.ExportService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ExportDao exportDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ContractProductDao contractProductDao;


    public Export findOne(String id) {//根据id查询
        return exportDao.findOne(id);
    }

    public void saveOrUpdate(Export export) {//保存或更新
        if (UtilFuns.isEmpty(export.getId())) {  //判断是否新增，根据对象id
            //设置默认值
            export.setInputDate(new Date());//制单日期
            export.setState(0);//海关报运：0 草稿状态 1 报运状态
            //设置报运的 购销合同 的状态为 2
            String[] contractIds = export.getContractIds().split(", ");
            StringBuilder sb = new StringBuilder();
            for (String contractId : contractIds) {
                Contract contract = contractDao.findOne(contractId);
                contract.setState(2);
                sb.append(contract.getContractNo()).append("，");
                contractDao.save(contract);
            }
            //设置客户要求的合同编号集合
            export.setCustomerContract(sb.substring(0,sb.length()-1).toString());


            //利用 打断设计 跳跃查询 获取所有 购销合同下面的产品订单及附件
            List<ContractProduct> contractProductList = contractProductDao.findCpByContract(contractIds);
            //购销合同 进行 数据搬家
            HashSet<ExportProduct> exportProducts = new HashSet<ExportProduct>();
            for (ContractProduct contractProduct : contractProductList) {
                ExportProduct exportProduct = new ExportProduct();
                BeanUtils.copyProperties(contractProduct,exportProduct);
                exportProduct.setId(null);// insert数据id必须为空
                exportProduct.setExport(export);// @OneToMany(mappedBy="export",cascade=CascadeType.ALL)  由多的一方主持维权关系(出口报运货物维护与货运单的关系)
                //复制完成的 报运商品 添加到集合中
                exportProducts.add(exportProduct);

                //购销合同下的附件 进行 数据搬家
                Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
                HashSet<ExtEproduct> extEproducts = new HashSet<ExtEproduct>();
                for (ExtCproduct extCproduct : extCproducts) {
                    ExtEproduct extEproduct = new ExtEproduct();
                    BeanUtils.copyProperties(extCproduct,extEproduct);
                    extEproduct.setId(null);
                    extEproduct.setExportProduct(exportProduct);
                    //复制完成的 报运商品附件 添加到集合中
                    extEproducts.add(extEproduct);
                }
                exportProduct.setExtEproducts(extEproducts);
            }
            export.setExportProducts(exportProducts);

        } else {

        }
        exportDao.save(export);
    }

    public void saveOrUpdateAll(Collection<Export> entitys) {//批量保存或更新
        for (Export export : entitys) {
            exportDao.save(export);
        }
    }

    public void deleteById(String id) {//根据id删除
        Export export = exportDao.findOne(id);
        String[] contractIds = export.getContractIds().split(", ");
        for (String contractId : contractIds) {
            Contract contract = contractDao.getOne(contractId);
            contract.setState(1);
            contractDao.save(contract);
        }
        exportDao.delete(id);
    }

    public void delete(String[] ids) {//批量删除
        for (String id : ids) {
            deleteById(id);
        }
    }

    //根据条件查询所有
    public List<Export> find(Specification<Export> spec) {
        return exportDao.findAll(spec);
    }

    //分页查询
    public Page<Export> findPage(Specification<Export> spec, Pageable pageable) {
        return exportDao.findAll(spec, pageable);
    }

    public void updateExportState(String[] ids, int state) {
        for (String id : ids) {
            Export export = exportDao.findOne(id);
            export.setState(state);
            exportDao.save(export);
        }
    }

	@Override
	public int findAcountByState(int state) {
		return exportDao.findAcountByState(state);
	}
}
