package ma.enova.rth.converter;


import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.AuditBusinessObject;
import ma.enova.rth.common.bean.BaseDto;
import ma.enova.rth.common.bean.BusinessObject;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<T extends BusinessObject, DTO extends BaseDto, H extends BusinessObject> {
    protected int maxLevel = 2;
    protected Class<T> itemType;
    protected Class<DTO> dtoType;
    protected Class<H> historyType;

    public AbstractConverter(Class<T> itemType, Class<DTO> dtoType, Class<H> historyType) {
        this.itemType = itemType;
        this.dtoType = dtoType;
        this.historyType = historyType;
    }

    public abstract T toItem(DTO dto);

    public abstract DTO toDto(T item);

    public List<T> toItem(List<DTO> dtos) {
        List<T> items = new ArrayList();
        if (dtos != null && !dtos.isEmpty()) {
            for (DTO DTO : dtos) {
                items.add(toItem(DTO));
            }
        }
        return items;
    }

    public T getById(DTO dto) {
        T result = null;
        if (dto != null) {
            try {
                result = itemType.getDeclaredConstructor(Long.class).newInstance(dto.getId());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public DTO getById(T t) {
        DTO result = null;
        if (t != null) {
            try {
                result = dtoType.getDeclaredConstructor(Long.class).newInstance(t.getId());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }


    public List<DTO> toDto(List<T> items) {
        if (items == null || items.isEmpty()) {
            return null;
        } else {
            List<DTO> dtos = new ArrayList();
            for (T item : items) {
                dtos.add(toDto(item));
            }
            return dtos;
        }
    }

    public void copyToDto(T t, DTO dto) {
        copy(t, dto);
    }

    public DTO copyToDto(T t) {
        DTO dto = RefelexivityUtil.<DTO>constructObjectUsingDefaultConstr(dtoType);
        copy(t, dto);
        return dto;
    }
    public DTO copyFromHistory(H h) {
        DTO dto = RefelexivityUtil.<DTO>constructObjectUsingDefaultConstr(dtoType);
        copyFromHistory(h, dto);
        return dto;
    }
    private void copy(T t, DTO dto) {
        if (t != null && dto != null && t instanceof AuditBusinessObject && dto instanceof AuditBaseDto) {
            AuditBusinessObject audited = (AuditBusinessObject) t;
            AuditBaseDto auditedDto = (AuditBaseDto) dto;
            auditedDto.setCreatedBy(audited.getCreatedBy());
            auditedDto.setCreatedOn(DateUtil.dateTimeToString(audited.getCreatedOn()));
            auditedDto.setUpdatedBy(audited.getUpdatedBy());
            auditedDto.setUpdatedOn(DateUtil.dateTimeToString(audited.getUpdatedOn()));
        }

    }

    private void copyFromHistory(H h, DTO dto) {
        if (h != null && dto != null && dto instanceof AuditBaseDto && h instanceof AuditBusinessObject) {
            AuditBusinessObject auditedHistory = (AuditBusinessObject) h;
            AuditBaseDto auditedDto = (AuditBaseDto) dto;
            auditedDto.setCreatedBy(auditedHistory.getCreatedBy());
            auditedDto.setCreatedOn(DateUtil.dateTimeToString(auditedHistory.getCreatedOn()));
            auditedDto.setUpdatedBy(auditedHistory.getUpdatedBy());
            auditedDto.setUpdatedOn(DateUtil.dateTimeToString(auditedHistory.getUpdatedOn()));
        }

    }

    public DTO copyIncludeExclude(DTO dtoSource, String[] includes, String[] excludes) {
        DTO dtoTarget = RefelexivityUtil.constructObjectUsingDefaultConstr(dtoType);
        return copy(dtoSource, dtoTarget, includes, excludes);
    }

    public List<DTO> copyIncludeExclude(List<DTO> dtoSources, String[] includes, String[] excludes) {
        return CollectionUtils.emptyIfNull(dtoSources).stream().map(dto -> copyIncludeExclude(dto, includes, excludes))
                .collect(Collectors.toList());
    }

    private DTO copy(DTO dtoSource, DTO dtoTarget, String[] includes, String[] excludes) {
        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(dtoSource, dtoTarget, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(dtoSource, dtoTarget, excludes);

            dtoTarget.setId(dtoSource.getId());
            return dtoTarget;
        }

        return dtoSource;
    }


    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
