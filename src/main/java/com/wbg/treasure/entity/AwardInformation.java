package com.wbg.treasure.entity;

public class AwardInformation {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column award_information.UUID
     *
     * @mbg.generated
     */
    private Integer uuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column award_information.page_uuid
     *
     * @mbg.generated
     */
    private String pageUuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column award_information.page_connent
     *
     * @mbg.generated
     */
    private String pageConnent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column award_information.get_time
     *
     * @mbg.generated
     */
    private String getTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column award_information.all_content
     *
     * @mbg.generated
     */
    private String allContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column award_information.get_statis
     *
     * @mbg.generated
     */
    private String getStatis;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column award_information.UUID
     *
     * @return the value of award_information.UUID
     *
     * @mbg.generated
     */
    public Integer getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column award_information.UUID
     *
     * @param uuid the value for award_information.UUID
     *
     * @mbg.generated
     */
    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column award_information.page_uuid
     *
     * @return the value of award_information.page_uuid
     *
     * @mbg.generated
     */
    public String getPageUuid() {
        return pageUuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column award_information.page_uuid
     *
     * @param pageUuid the value for award_information.page_uuid
     *
     * @mbg.generated
     */
    public void setPageUuid(String pageUuid) {
        this.pageUuid = pageUuid == null ? null : pageUuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column award_information.page_connent
     *
     * @return the value of award_information.page_connent
     *
     * @mbg.generated
     */
    public String getPageConnent() {
        return pageConnent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column award_information.page_connent
     *
     * @param pageConnent the value for award_information.page_connent
     *
     * @mbg.generated
     */
    public void setPageConnent(String pageConnent) {
        this.pageConnent = pageConnent == null ? null : pageConnent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column award_information.get_time
     *
     * @return the value of award_information.get_time
     *
     * @mbg.generated
     */
    public String getGetTime() {
        return getTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column award_information.get_time
     *
     * @param getTime the value for award_information.get_time
     *
     * @mbg.generated
     */
    public void setGetTime(String getTime) {
        this.getTime = getTime == null ? null : getTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column award_information.all_content
     *
     * @return the value of award_information.all_content
     *
     * @mbg.generated
     */
    public String getAllContent() {
        return allContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column award_information.all_content
     *
     * @param allContent the value for award_information.all_content
     *
     * @mbg.generated
     */
    public void setAllContent(String allContent) {
        this.allContent = allContent == null ? null : allContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column award_information.get_statis
     *
     * @return the value of award_information.get_statis
     *
     * @mbg.generated
     */
    public String getGetStatis() {
        return getStatis;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column award_information.get_statis
     *
     * @param getStatis the value for award_information.get_statis
     *
     * @mbg.generated
     */
    public void setGetStatis(String getStatis) {
        this.getStatis = getStatis == null ? null : getStatis.trim();
    }
}