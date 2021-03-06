package com.example.productcatalog.dao.repoService;

import com.example.productcatalog.dao.entity.Catalog;
import com.example.productcatalog.dao.repository.CatalogRepository;
import com.example.productcatalog.dto.CatalogDTO;
import com.example.productcatalog.dto.DataTableRequestDTO;
import com.example.productcatalog.dto.DataTableResponseDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@CommonsLog
public class CatalogRepoService extends BaseRepoService<Catalog, Long> {

    @Autowired
    CatalogRepository catalogRepository;

    @Override
    protected JpaRepository<Catalog, Long> getRepository() {
        return catalogRepository;
    }

    @Override
    protected Class<Catalog> getEntityClass() {
        return Catalog.class;
    }

    @SuppressWarnings("unchecked")
    public DataTableResponseDTO<List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO) {
        Criteria criteria = getCriteria();
        CatalogDTO catalogDTO = requestDTO.getQuery();
        if (catalogDTO != null) {
            if (catalogDTO.getId() != null) {
                criteria.add(Restrictions.eq("id", catalogDTO.getId()));
            }
            if (!StringUtils.isEmpty(catalogDTO.getName())) {
                criteria.add(Restrictions.ilike("name", catalogDTO.getName(), MatchMode.ANYWHERE));
            }
        }
        Long filteredFrom = count(criteria);
        addPagingAndSorting(criteria, requestDTO);
        addProjection(criteria);
        criteria.setResultTransformer(Transformers.aliasToBean(CatalogDTO.class));
        List<CatalogDTO> criteriaResult = (List<CatalogDTO>) criteria.list();
        return DataTableResponseDTO.getInstance(count(), criteriaResult, filteredFrom, requestDTO);
    }

    private void addProjection(Criteria criteria) {
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
        );
    }
}
