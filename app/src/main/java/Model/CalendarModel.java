package Model;

public class CalendarModel {
    public String Name;
    public String Description;
    public String DatePlanted;
    public String HarvestDate;
    public String Status;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDatePlanted() {
        return DatePlanted;
    }

    public void setDatePlanted(String datePlanted) {
        DatePlanted = datePlanted;
    }

    public String getHarvestDate() {
        return HarvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        HarvestDate = harvestDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
