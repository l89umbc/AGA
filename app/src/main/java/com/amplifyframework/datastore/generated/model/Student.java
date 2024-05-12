package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Student type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Students", type = Model.Type.USER, version = 1)
public final class Student implements Model {
  public static final QueryField ID = field("Student", "id");
  public static final QueryField UMBC_ID = field("Student", "umbcID");
  public static final QueryField NAME = field("Student", "name");
  public static final QueryField STUDENT_CLASS_STUDENTS_ID = field("Student", "studentClassStudentsId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String umbcID;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String studentClassStudentsId;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getUmbcId() {
      return umbcID;
  }
  
  public String getName() {
      return name;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getStudentClassStudentsId() {
      return studentClassStudentsId;
  }
  
  private Student(String id, String umbcID, String name, String studentClassStudentsId) {
    this.id = id;
    this.umbcID = umbcID;
    this.name = name;
    this.studentClassStudentsId = studentClassStudentsId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Student student = (Student) obj;
      return ObjectsCompat.equals(getId(), student.getId()) &&
              ObjectsCompat.equals(getUmbcId(), student.getUmbcId()) &&
              ObjectsCompat.equals(getName(), student.getName()) &&
              ObjectsCompat.equals(getCreatedAt(), student.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), student.getUpdatedAt()) &&
              ObjectsCompat.equals(getStudentClassStudentsId(), student.getStudentClassStudentsId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUmbcId())
      .append(getName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getStudentClassStudentsId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Student {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("umbcID=" + String.valueOf(getUmbcId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("studentClassStudentsId=" + String.valueOf(getStudentClassStudentsId()))
      .append("}")
      .toString();
  }
  
  public static UmbcIdStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Student justId(String id) {
    return new Student(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      umbcID,
      name,
      studentClassStudentsId);
  }
  public interface UmbcIdStep {
    NameStep umbcId(String umbcId);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Student build();
    BuildStep id(String id);
    BuildStep studentClassStudentsId(String studentClassStudentsId);
  }
  

  public static class Builder implements UmbcIdStep, NameStep, BuildStep {
    private String id;
    private String umbcID;
    private String name;
    private String studentClassStudentsId;
    public Builder() {
      
    }
    
    private Builder(String id, String umbcID, String name, String studentClassStudentsId) {
      this.id = id;
      this.umbcID = umbcID;
      this.name = name;
      this.studentClassStudentsId = studentClassStudentsId;
    }
    
    @Override
     public Student build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Student(
          id,
          umbcID,
          name,
          studentClassStudentsId);
    }
    
    @Override
     public NameStep umbcId(String umbcId) {
        Objects.requireNonNull(umbcId);
        this.umbcID = umbcId;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep studentClassStudentsId(String studentClassStudentsId) {
        this.studentClassStudentsId = studentClassStudentsId;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String umbcId, String name, String studentClassStudentsId) {
      super(id, umbcID, name, studentClassStudentsId);
      Objects.requireNonNull(umbcID);
      Objects.requireNonNull(name);
    }
    
    @Override
     public CopyOfBuilder umbcId(String umbcId) {
      return (CopyOfBuilder) super.umbcId(umbcId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder studentClassStudentsId(String studentClassStudentsId) {
      return (CopyOfBuilder) super.studentClassStudentsId(studentClassStudentsId);
    }
  }
  

  public static class StudentIdentifier extends ModelIdentifier<Student> {
    private static final long serialVersionUID = 1L;
    public StudentIdentifier(String id) {
      super(id);
    }
  }
  
}
