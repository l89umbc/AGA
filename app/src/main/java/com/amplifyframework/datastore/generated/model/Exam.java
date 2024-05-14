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

/** This is an auto generated class representing the Exam type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Exams", type = Model.Type.USER, version = 1)
public final class Exam implements Model {
  public static final QueryField ID = field("Exam", "id");
  public static final QueryField NAME = field("Exam", "name");
  public static final QueryField STUDENT_CLASS_EXAMS_ID = field("Exam", "studentClassExamsId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="StudentExam") @HasMany(associatedWith = "examStudentExamId", type = StudentExam.class) List<StudentExam> studentExam = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String studentClassExamsId;
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
  
  public List<StudentExam> getStudentExam() {
      return studentExam;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getStudentClassExamsId() {
      return studentClassExamsId;
  }
  
  private Exam(String id, String name, String studentClassExamsId) {
    this.id = id;
    this.name = name;
    this.studentClassExamsId = studentClassExamsId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Exam exam = (Exam) obj;
      return ObjectsCompat.equals(getId(), exam.getId()) &&
              ObjectsCompat.equals(getName(), exam.getName()) &&
              ObjectsCompat.equals(getCreatedAt(), exam.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), exam.getUpdatedAt()) &&
              ObjectsCompat.equals(getStudentClassExamsId(), exam.getStudentClassExamsId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getStudentClassExamsId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Exam {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("studentClassExamsId=" + String.valueOf(getStudentClassExamsId()))
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
  public static Exam justId(String id) {
    return new Exam(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      studentClassExamsId);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Exam build();
    BuildStep id(String id);
    BuildStep studentClassExamsId(String studentClassExamsId);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private String studentClassExamsId;
    public Builder() {
      
    }
    
    private Builder(String id, String name, String studentClassExamsId) {
      this.id = id;
      this.name = name;
      this.studentClassExamsId = studentClassExamsId;
    }
    
    @Override
     public Exam build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Exam(
          id,
          name,
          studentClassExamsId);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep studentClassExamsId(String studentClassExamsId) {
        this.studentClassExamsId = studentClassExamsId;
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
    private CopyOfBuilder(String id, String name, String studentClassExamsId) {
      super(id, name, studentClassExamsId);
      Objects.requireNonNull(name);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder studentClassExamsId(String studentClassExamsId) {
      return (CopyOfBuilder) super.studentClassExamsId(studentClassExamsId);
    }
  }
  

  public static class ExamIdentifier extends ModelIdentifier<Exam> {
    private static final long serialVersionUID = 1L;
    public ExamIdentifier(String id) {
      super(id);
    }
  }
  
}
