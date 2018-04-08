package cn.tzs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.tzs.domain.Invoice;


public interface InvoiceDao  extends JpaRepository<Invoice, String>,JpaSpecificationExecutor<Invoice> {

}
