package com.example.productcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataTableResponseDTO<T> {

    private static final String ASC = "asc";
    private static final String defaultSortColumn = "createdDate";
    /**
     * recordsTotal is the total records in the table of database.
     */
    protected long recordsTotal;
    /**
     * recordsFiltered is the total number of records which meet current search criteria.
     */

    protected long recordsFiltered;
    private String sortColumn;
    private String sortOrder;
    private Integer pageSize;
    private Integer pageIndex;
    private Boolean fetchAllRecords = Boolean.FALSE;
    private Boolean status = true;
    private String message;
    private T data;
    private List<ErrorDTO> errors = new ArrayList<>();


    public static <T> DataTableResponseDTO<List<T>> getInstance(long recordsTotal, List<T> data, long recordsFiltered,
                                                                DataTableRequestDTO dataTableRequestDTO) {
        DataTableResponseDTO<List<T>> dataTableResponseDTO = new DataTableResponseDTO<>();
        dataTableResponseDTO.setPageIndex(dataTableRequestDTO.getPageIndex());
        dataTableResponseDTO.setSortColumn(dataTableRequestDTO.getSortColumn());
        dataTableResponseDTO.setPageSize(dataTableRequestDTO.getPageSize());
        dataTableResponseDTO.setSortOrder(dataTableRequestDTO.getSortOrder());
        return setInstance(recordsTotal, data, recordsFiltered, dataTableResponseDTO);
    }

    public static <T> DataTableResponseDTO<List<T>> getInstance(long recordsTotal, List<T> data, long recordsFiltered) {
        DataTableResponseDTO<List<T>> dataTableResponseDTO = new DataTableResponseDTO<List<T>>();
        return setInstance(recordsTotal, data, recordsFiltered, dataTableResponseDTO);
    }

    private static <T> DataTableResponseDTO<List<T>> setInstance(long recordsTotal,
                                                                 List<T> data,
                                                                 long recordsFiltered,
                                                                 DataTableResponseDTO<List<T>> dataTableResponseDTO) {
        if (dataTableResponseDTO == null) {
            dataTableResponseDTO = new DataTableResponseDTO<>();
        }
        dataTableResponseDTO.setRecordsTotal(recordsTotal);
        dataTableResponseDTO.setData(data);
        dataTableResponseDTO.setRecordsFiltered(recordsFiltered);
        dataTableResponseDTO.setStatus(true);
        if (CollectionUtils.isEmpty(data)) {
            dataTableResponseDTO.setMessage("No record found");
        } else {
            dataTableResponseDTO.setMessage("Request processed successfully");
        }
        return dataTableResponseDTO;
    }

    public static <T> DataTableResponseDTO<List<T>> getInstance(Boolean status, String message) {
        DataTableResponseDTO<List<T>> dataTableResponseDTO = new DataTableResponseDTO<>();
        dataTableResponseDTO.setStatus(status);
        dataTableResponseDTO.setMessage(message);
        return dataTableResponseDTO;
    }


    /*getter/setter used for decorating XML request/response --STARTS--*/
    public Collection getDataForXmlAsCollection() {
        System.out.println("?????????? Inside getDataForXmlAsCollection() method of " + this.getClass());
        if (this.data instanceof Collection) {
            return (Collection) this.data;
        }
        return null;
    }

    public void setDataForXmlAsCollection(T dataForXmlAsCollection) {
        this.data = dataForXmlAsCollection;
    }

    public T getDataForXmlAsSingleNode() {
        System.out.println("?????????? Inside getDataForXmlAsSingleNode() method of " + this.getClass());
        if (!(this.data instanceof Collection)) {
            return this.data;
        }
        return null;
    }

    public void setDataForXmlAsSingleNode(T dataForXmlAsSingleNode) {
        this.data = dataForXmlAsSingleNode;
    }
    /*getter/setter used for decorating XML request/response --ENDS--*/

}
