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

/** This is an auto generated class representing the StudentExam type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "StudentExams", type = Model.Type.USER, version = 1)
public final class StudentExam implements Model {
  public static final QueryField ID = field("StudentExam", "id");
  public static final QueryField STUDENT_NAME = field("StudentExam", "studentName");
  public static final QueryField GRADE = field("StudentExam", "grade");
  public static final QueryField EXAM_STUDENT_EXAM_ID = field("StudentExam", "examStudentExamId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String studentName;
  private final @ModelField(targetType="String", isRequired = true) String grade;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String examStudentExamId;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getStudentName() {
      return studentName;
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
  
  private StudentExam(String id, String studentName, String grade, String examStudentExamId) {
    this.id = id;
    this.studentName = studentName;
    this.grade = grade;
    this.examStudentExamId = examStudentExamId;
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
              ObjectsCompat.equals(getStudentName(), studentExam.getStudentName()) &&
              ObjectsCompat.equals(getGrade(), studentExam.getGrade()) &&
              ObjectsCompat.equals(getCreatedAt(), studentExam.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), studentExam.getUpdatedAt()) &&
              ObjectsCompat.equals(getExamStudentExamId(), studentExam.getExamStudentExamId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getStudentName())
      .append(getGrade())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getExamStudentExamId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("StudentExam {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("studentName=" + String.valueOf(getStudentName()) + ", ")
      .append("grade=" + String.valueOf(getGrade()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("examStudentExamId=" + String.valueOf(getExamStudentExamId()))
      .append("}")
      .toString();
  }
  
  public static StudentNameStep builder() {
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
      studentName,
      grade,
      examStudentExamId);
  }
  public interface StudentNameStep {
    GradeStep studentName(String studentName);
  }
  

  public interface GradeStep {
    BuildStep grade(String grade);
  }
  

  public interface BuildStep {
    StudentExam build();
    BuildStep id(String id);
    BuildStep examStudentExamId(String examStudentExamId);
  }
  

  public static class Builder implements StudentNameStep, GradeStep, BuildStep {
    private String id;
    private String studentName;
    private String grade;
    private String examStudentExamId;
    public Builder() {
      
    }
    
    private Builder(String id, String studentName, String grade, String examStudentExamId) {
      this.id = id;
      this.studentName = studentName;
      this.grade = grade;
      this.examStudentExamId = examStudentExamId;
    }
    
    @Override
     public StudentExam build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new StudentExam(
          id,
          studentName,
          grade,
          examStudentExamId);
    }
    
    @Override
     public GradeStep studentName(String studentName) {
        Objects.requireNonNull(studentName);
        this.studentName = studentName;
        return this;
    }
    
    @Override
     public BuildStep grade(String grade) {
        Objects.requireNonNull(grade);
        this.grade = grade;
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
    private CopyOfBuilder(String id, String studentName, String grade, String examStudentExamId) {
      super(id, studentName, grade, examStudentExamId);
      Objects.requireNonNull(studentName);
      Objects.requireNonNull(grade);
    }
    
    @Override
     public CopyOfBuilder studentName(String studentName) {
      return (CopyOfBuilder) super.studentName(studentName);
    }
    
    @Override
     public CopyOfBuilder grade(String grade) {
      return (CopyOfBuilder) super.grade(grade);
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
