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
  public static final QueryField NAME = field("Student", "name");
  public static final QueryField STUDENT_CLASS_STUDENTS_ID = field("Student", "studentClassStudentsId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
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
  
  private Student(String id, String name, String studentClassStudentsId) {
    this.id = id;
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
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("studentClassStudentsId=" + String.valueOf(getStudentClassStudentsId()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      studentClassStudentsId);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Student build();
    BuildStep id(String id);
    BuildStep studentClassStudentsId(String studentClassStudentsId);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private String studentClassStudentsId;
    public Builder() {
      
    }
    
    private Builder(String id, String name, String studentClassStudentsId) {
      this.id = id;
      this.name = name;
      this.studentClassStudentsId = studentClassStudentsId;
    }
    
    @Override
     public Student build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Student(
          id,
          name,
          studentClassStudentsId);
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
    private CopyOfBuilder(String id, String name, String studentClassStudentsId) {
      super(id, name, studentClassStudentsId);
      Objects.requireNonNull(name);
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
