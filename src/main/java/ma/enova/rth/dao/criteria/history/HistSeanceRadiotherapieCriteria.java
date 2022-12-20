package ma.enova.rth.dao.criteria.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import ma.enova.rth.common.bean.BaseCriteria;

import java.time.LocalDateTime;

/**
 * Critère HistSeanceRadiotherapie.
 */
public class HistSeanceRadiotherapieCriteria extends BaseCriteria {

    /**
     * Fields.
     */

    private String objectName;
    private String objectNameLike;
    private String data;
    private String dataLike;
    private String userId;
    private String username;
    private String usernameLike;
    private String actionType;
    private String actionTypeLike;
    private String objectId;
    private LocalDateTime date;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;


    /**
     * Methods.
     */

    public String getObjectNameLike() {
        return this.objectNameLike;
    }

    public void setObjectNameLike(String objectNameLike) {
        this.objectNameLike = objectNameLike;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getDataLike() {
        return this.dataLike;
    }

    public void setDataLike(String dataLike) {
        this.dataLike = dataLike;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsernameLike() {
        return this.usernameLike;
    }

    public void setUsernameLike(String usernameLike) {
        this.usernameLike = usernameLike;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActionTypeLike() {
        return this.actionTypeLike;
    }

    public void setActionTypeLike(String actionTypeLike) {
        this.actionTypeLike = actionTypeLike;
    }

    public String getActionType() {
        return this.actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateTo() {
        return this.dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

}