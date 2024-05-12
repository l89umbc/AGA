package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the StudentClass type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "StudentClasses", type = Model.Type.USER, version = 1)
public final class StudentClass implements Model {
  public static final QueryField ID = field("StudentClass", "id");
  public static final QueryField NAME = field("StudentClass", "name");
  public static final QueryField PROFESSOR_CLASSES_ID = field("StudentClass", "professorClassesId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Student") @HasMany(associatedWith = "studentClassStudentsId", type = Student.class) List<Student> students = null;
  private final @ModelField(targetType="Exam") @HasMany(associatedWith = "studentClassExamsId", type = Exam.class) List<Exam> exams = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String professorClassesId;
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
  
  public List<Student> getStudents() {
      return students;
  }
  
  public List<Exam> getExams() {
      return exams;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getProfessorClassesId() {
      return professorClassesId;
  }
  
  private StudentClass(String id, String name, String professorClassesId) {
    this.id = id;
    this.name = name;
    this.professorClassesId = professorClassesId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      StudentClass studentClass = (StudentClass) obj;
      return ObjectsCompat.equals(getId(), studentClass.getId()) &&
              ObjectsCompat.equals(getName(), studentClass.getName()) &&
              ObjectsCompat.equals(getCreatedAt(), studentClass.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), studentClass.getUpdatedAt()) &&
              ObjectsCompat.equals(getProfessorClassesId(), studentClass.getProfessorClassesId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getProfessorClassesId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("StudentClass {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("professorClassesId=" + String.valueOf(getProfessorClassesId()))
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
  public static StudentClass justId(String id) {
    return new StudentClass(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      professorClassesId);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    StudentClass build();
    BuildStep id(String id);
    BuildStep professorClassesId(String professorClassesId);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private String professorClassesId;
    public Builder() {
      
    }
    
    private Builder(String id, String name, String professorClassesId) {
      this.id = id;
      this.name = name;
      this.professorClassesId = professorClassesId;
    }
    
    @Override
     public StudentClass build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new StudentClass(
          id,
          name,
          professorClassesId);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep professorClassesId(String professorClassesId) {
        this.professorClassesId = professorClassesId;
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
    private CopyOfBuilder(String id, String name, String professorClassesId) {
      super(id, name, professorClassesId);
      Objects.requireNonNull(name);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder professorClassesId(String professorClassesId) {
      return (CopyOfBuilder) super.professorClassesId(professorClassesId);
    }
  }
  

  public static class StudentClassIdentifier extends ModelIdentifier<StudentClass> {
    private static final long serialVersionUID = 1L;
    public StudentClassIdentifier(String id) {
      super(id);
    }
  }
  
}
