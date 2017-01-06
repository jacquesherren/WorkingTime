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
 * (build: 2016-12-13 18:16:55 UTC)
 * on 2017-01-06 at 03:22:05 UTC 
 * Modify at your own risk.
 */

package ch.hevs.androidproject644.js.workingtime.backend.workerApi.model;

/**
 * Model definition for Worker.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the workerApi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Worker extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_active")
  private java.lang.Boolean active;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_birthdate") @com.google.api.client.json.JsonString
  private java.lang.Long birthdate;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_duration") @com.google.api.client.json.JsonString
  private java.lang.Long duration;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_firstname")
  private java.lang.String firstname;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_id") @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_lastname")
  private java.lang.String lastname;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_sex")
  private java.lang.String sex;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getActive() {
    return active;
  }

  /**
   * @param active active or {@code null} for none
   */
  public Worker setActive(java.lang.Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getBirthdate() {
    return birthdate;
  }

  /**
   * @param birthdate birthdate or {@code null} for none
   */
  public Worker setBirthdate(java.lang.Long birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getDuration() {
    return duration;
  }

  /**
   * @param duration duration or {@code null} for none
   */
  public Worker setDuration(java.lang.Long duration) {
    this.duration = duration;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFirstname() {
    return firstname;
  }

  /**
   * @param firstname firstname or {@code null} for none
   */
  public Worker setFirstname(java.lang.String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Worker setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLastname() {
    return lastname;
  }

  /**
   * @param lastname lastname or {@code null} for none
   */
  public Worker setLastname(java.lang.String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSex() {
    return sex;
  }

  /**
   * @param sex sex or {@code null} for none
   */
  public Worker setSex(java.lang.String sex) {
    this.sex = sex;
    return this;
  }

  @Override
  public Worker set(String fieldName, Object value) {
    return (Worker) super.set(fieldName, value);
  }

  @Override
  public Worker clone() {
    return (Worker) super.clone();
  }

}
