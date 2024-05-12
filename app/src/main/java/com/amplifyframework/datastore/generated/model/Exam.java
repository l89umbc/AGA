package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasOne;
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
  public static final QueryField GRADE = field("Exam", "grade");
  public static final QueryField STUDENT_CLASS_EXAMS_ID = field("Exam", "studentClassExamsId");
  public static final QueryField EXAM_STUDENT_ID = field("Exam", "examStudentId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Student", isRequired = true) @HasOne(associatedWith = "id", type = Student.class) Student student = null;
  private final @ModelField(targetType="String", isRequired = true) String grade;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String studentClassExamsId;
  private final @ModelField(targetType="ID", isRequired = true) String examStudentId;
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
  
  public Student getStudent() {
      return student;
  }
  
  public String getGrade() {
      return grade;
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
  
  public String getExamStudentId() {
      return examStudentId;
  }
  
  private Exam(String id, String name, String grade, String studentClassExamsId, String examStudentId) {
    this.id = id;
    this.name = name;
    this.grade = grade;
    this.studentClassExamsId = studentClassExamsId;
    this.examStudentId = examStudentId;
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
              ObjectsCompat.equals(getGrade(), exam.getGrade()) &&
              ObjectsCompat.equals(getCreatedAt(), exam.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), exam.getUpdatedAt()) &&
              ObjectsCompat.equals(getStudentClassExamsId(), exam.getStudentClassExamsId()) &&
              ObjectsCompat.equals(getExamStudentId(), exam.getExamStudentId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getGrade())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getStudentClassExamsId())
      .append(getExamStudentId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Exam {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("grade=" + String.valueOf(getGrade()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("studentClassExamsId=" + String.valueOf(getStudentClassExamsId()) + ", ")
      .append("examStudentId=" + String.valueOf(getExamStudentId()))
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
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      grade,
      studentClassExamsId,
      examStudentId);
  }
  public interface NameStep {
    GradeStep name(String name);
  }
  

  public interface GradeStep {
    ExamStudentIdStep grade(String grade);
  }
  

  public interface ExamStudentIdStep {
    BuildStep examStudentId(String examStudentId);
  }
  

  public interface BuildStep {
    Exam build();
    BuildStep id(String id);
    BuildStep studentClassExamsId(String studentClassExamsId);
  }
  

  public static class Builder implements NameStep, GradeStep, ExamStudentIdStep, BuildStep {
    private String id;
    private String name;
    private String grade;
    private String examStudentId;
    private String studentClassExamsId;
    public Builder() {
      
    }
    
    private Builder(String id, String name, String grade, String studentClassExamsId, String examStudentId) {
      this.id = id;
      this.name = name;
      this.grade = grade;
      this.studentClassExamsId = studentClassExamsId;
      this.examStudentId = examStudentId;
    }
    
    @Override
     public Exam build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Exam(
          id,
          name,
          grade,
          studentClassExamsId,
          examStudentId);
    }
    
    @Override
     public GradeStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public ExamStudentIdStep grade(String grade) {
        Objects.requireNonNull(grade);
        this.grade = grade;
        return this;
    }
    
    @Override
     public BuildStep examStudentId(String examStudentId) {
        Objects.requireNonNull(examStudentId);
        this.examStudentId = examStudentId;
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
    private CopyOfBuilder(String id, String name, String grade, String studentClassExamsId, String examStudentId) {
      super(id, name, grade, studentClassExamsId, examStudentId);
      Objects.requireNonNull(name);
      Objects.requireNonNull(grade);
      Objects.requireNonNull(examStudentId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder grade(String grade) {
      return (CopyOfBuilder) super.grade(grade);
    }
    
    @Override
     public CopyOfBuilder examStudentId(String examStudentId) {
      return (CopyOfBuilder) super.examStudentId(examStudentId);
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
