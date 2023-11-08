package Model;

public class CalendarModel {
    public String CropName;
    public String Description;

    public CalendarModel(){}
    public CalendarModel(String CropName,String Description,String CompletionDate){
        this.CropName = CropName;
        this.Description = Description;
        this.CompletionDate = CompletionDate;
    }

    public String getCropName() {
        return CropName;
    }

    public void setCropName(String cropName) {
        CropName = cropName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCompletionDate() {
        return CompletionDate;
    }

    public void setCompletionDate(String completionDate) {
        CompletionDate = completionDate;
    }

    public String CompletionDate;








}
