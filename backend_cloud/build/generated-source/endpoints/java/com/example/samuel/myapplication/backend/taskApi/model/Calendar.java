/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-10-17 16:43:55 UTC)
 * on 2016-12-23 at 08:44:22 UTC 
 * Modify at your own risk.
 */

package com.example.samuel.myapplication.backend.taskApi.model;

/**
 * Model definition for Calendar.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the taskApi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Calendar extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String calendarType;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer fieldsComputed;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer fieldsNormalized;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer firstDayOfWeek;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean lenient;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer minimalDaysInFirstWeek;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime time;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long timeInMillis;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private TimeZone timeZone;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Locale weekCountData;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean weekDateSupported;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer weekYear;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer weeksInWeekYear;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean zoneShared;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCalendarType() {
    return calendarType;
  }

  /**
   * @param calendarType calendarType or {@code null} for none
   */
  public Calendar setCalendarType(java.lang.String calendarType) {
    this.calendarType = calendarType;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getFieldsComputed() {
    return fieldsComputed;
  }

  /**
   * @param fieldsComputed fieldsComputed or {@code null} for none
   */
  public Calendar setFieldsComputed(java.lang.Integer fieldsComputed) {
    this.fieldsComputed = fieldsComputed;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getFieldsNormalized() {
    return fieldsNormalized;
  }

  /**
   * @param fieldsNormalized fieldsNormalized or {@code null} for none
   */
  public Calendar setFieldsNormalized(java.lang.Integer fieldsNormalized) {
    this.fieldsNormalized = fieldsNormalized;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getFirstDayOfWeek() {
    return firstDayOfWeek;
  }

  /**
   * @param firstDayOfWeek firstDayOfWeek or {@code null} for none
   */
  public Calendar setFirstDayOfWeek(java.lang.Integer firstDayOfWeek) {
    this.firstDayOfWeek = firstDayOfWeek;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getLenient() {
    return lenient;
  }

  /**
   * @param lenient lenient or {@code null} for none
   */
  public Calendar setLenient(java.lang.Boolean lenient) {
    this.lenient = lenient;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getMinimalDaysInFirstWeek() {
    return minimalDaysInFirstWeek;
  }

  /**
   * @param minimalDaysInFirstWeek minimalDaysInFirstWeek or {@code null} for none
   */
  public Calendar setMinimalDaysInFirstWeek(java.lang.Integer minimalDaysInFirstWeek) {
    this.minimalDaysInFirstWeek = minimalDaysInFirstWeek;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getTime() {
    return time;
  }

  /**
   * @param time time or {@code null} for none
   */
  public Calendar setTime(com.google.api.client.util.DateTime time) {
    this.time = time;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getTimeInMillis() {
    return timeInMillis;
  }

  /**
   * @param timeInMillis timeInMillis or {@code null} for none
   */
  public Calendar setTimeInMillis(java.lang.Long timeInMillis) {
    this.timeInMillis = timeInMillis;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public TimeZone getTimeZone() {
    return timeZone;
  }

  /**
   * @param timeZone timeZone or {@code null} for none
   */
  public Calendar setTimeZone(TimeZone timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Locale getWeekCountData() {
    return weekCountData;
  }

  /**
   * @param weekCountData weekCountData or {@code null} for none
   */
  public Calendar setWeekCountData(Locale weekCountData) {
    this.weekCountData = weekCountData;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getWeekDateSupported() {
    return weekDateSupported;
  }

  /**
   * @param weekDateSupported weekDateSupported or {@code null} for none
   */
  public Calendar setWeekDateSupported(java.lang.Boolean weekDateSupported) {
    this.weekDateSupported = weekDateSupported;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getWeekYear() {
    return weekYear;
  }

  /**
   * @param weekYear weekYear or {@code null} for none
   */
  public Calendar setWeekYear(java.lang.Integer weekYear) {
    this.weekYear = weekYear;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getWeeksInWeekYear() {
    return weeksInWeekYear;
  }

  /**
   * @param weeksInWeekYear weeksInWeekYear or {@code null} for none
   */
  public Calendar setWeeksInWeekYear(java.lang.Integer weeksInWeekYear) {
    this.weeksInWeekYear = weeksInWeekYear;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getZoneShared() {
    return zoneShared;
  }

  /**
   * @param zoneShared zoneShared or {@code null} for none
   */
  public Calendar setZoneShared(java.lang.Boolean zoneShared) {
    this.zoneShared = zoneShared;
    return this;
  }

  @Override
  public Calendar set(String fieldName, Object value) {
    return (Calendar) super.set(fieldName, value);
  }

  @Override
  public Calendar clone() {
    return (Calendar) super.clone();
  }

}
