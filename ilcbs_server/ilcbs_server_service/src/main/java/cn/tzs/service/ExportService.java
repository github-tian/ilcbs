package cn.tzs.service;

import cn.tzs.domain.Export;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;

public interface ExportService extends BaseService<Export> {

    void updateExportState(String[] ids, int state);

	int findAcountByState(int i);
}
