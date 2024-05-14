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

/** This is an auto generated class representing the StudentExam type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "StudentExams", type = Model.Type.USER, version = 1)
public final class StudentExam implements Model {
  public static final QueryField ID = field("StudentExam", "id");
  public static final QueryField GRADE = field("StudentExam", "grade");
  public static final QueryField EXAM_STUDENT_EXAM_ID = field("StudentExam", "examStudentExamId");
  public static final QueryField STUDENT_EXAM_STUDENT_ID = field("StudentExam", "studentExamStudentId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Student", isRequired = true) @HasOne(associatedWith = "id", type = Student.class) Student student = null;
  private final @ModelField(targetType="String", isRequired = true) String grade;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String examStudentExamId;
  private final @ModelField(targetType="ID", isRequired = true) String studentExamStudentId;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
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
  
  public String getExamStudentExamId() {
      return examStudentExamId;
  }
  
  public String getStudentExamStudentId() {
      return studentExamStudentId;
  }
  
  private StudentExam(String id, String grade, String examStudentExamId, String studentExamStudentId) {
    this.id = id;
    this.grade = grade;
    this.examStudentExamId = examStudentExamId;
    this.studentExamStudentId = studentExamStudentId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      StudentExam studentExam = (StudentExam) obj;
      return ObjectsCompat.equals(getId(), studentExam.getId()) &&
              ObjectsCompat.equals(getGrade(), studentExam.getGrade()) &&
              ObjectsCompat.equals(getCreatedAt(), studentExam.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), studentExam.getUpdatedAt()) &&
              ObjectsCompat.equals(getExamStudentExamId(), studentExam.getExamStudentExamId()) &&
              ObjectsCompat.equals(getStudentExamStudentId(), studentExam.getStudentExamStudentId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getGrade())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getExamStudentExamId())
      .append(getStudentExamStudentId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("StudentExam {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("grade=" + String.valueOf(getGrade()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("examStudentExamId=" + String.valueOf(getExamStudentExamId()) + ", ")
      .append("studentExamStudentId=" + String.valueOf(getStudentExamStudentId()))
      .append("}")
      .toString();
  }
  
  public static GradeStep builder() {
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
  public static StudentExam justId(String id) {
    return new StudentExam(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      grade,
      examStudentExamId,
      studentExamStudentId);
  }
  public interface GradeStep {
    StudentExamStudentIdStep grade(String grade);
  }
  

  public interface StudentExamStudentIdStep {
    BuildStep studentExamStudentId(String studentExamStudentId);
  }
  

  public interface BuildStep {
    StudentExam build();
    BuildStep id(String id);
    BuildStep examStudentExamId(String examStudentExamId);
  }
  

  public static class Builder implements GradeStep, StudentExamStudentIdStep, BuildStep {
    private String id;
    private String grade;
    private String studentExamStudentId;
    private String examStudentExamId;
    public Builder() {
      
    }
    
    private Builder(String id, String grade, String examStudentExamId, String studentExamStudentId) {
      this.id = id;
      this.grade = grade;
      this.examStudentExamId = examStudentExamId;
      this.studentExamStudentId = studentExamStudentId;
    }
    
    @Override
     public StudentExam build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new StudentExam(
          id,
          grade,
          examStudentExamId,
          studentExamStudentId);
    }
    
    @Override
     public StudentExamStudentIdStep grade(String grade) {
        Objects.requireNonNull(grade);
        this.grade = grade;
        return this;
    }
    
    @Override
     public BuildStep studentExamStudentId(String studentExamStudentId) {
        Objects.requireNonNull(studentExamStudentId);
        this.studentExamStudentId = studentExamStudentId;
        return this;
    }
    
    @Override
     public BuildStep examStudentExamId(String examStudentExamId) {
        this.examStudentExamId = examStudentExamId;
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
    private CopyOfBuilder(String id, String grade, String examStudentExamId, String studentExamStudentId) {
      super(id, grade, examStudentExamId, studentExamStudentId);
      Objects.requireNonNull(grade);
      Objects.requireNonNull(studentExamStudentId);
    }
    
    @Override
     public CopyOfBuilder grade(String grade) {
      return (CopyOfBuilder) super.grade(grade);
    }
    
    @Override
     public CopyOfBuilder studentExamStudentId(String studentExamStudentId) {
      return (CopyOfBuilder) super.studentExamStudentId(studentExamStudentId);
    }
    
    @Override
     public CopyOfBuilder examStudentExamId(String examStudentExamId) {
      return (CopyOfBuilder) super.examStudentExamId(examStudentExamId);
    }
  }
  

  public static class StudentExamIdentifier extends ModelIdentifier<StudentExam> {
    private static final long serialVersionUID = 1L;
    public StudentExamIdentifier(String id) {
      super(id);
    }
  }
  
}
