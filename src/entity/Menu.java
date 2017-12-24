package entity;

public class Menu {
    String foodID;
    String foodName;
    double price;
    String description;
    int calories;
    String promoInfo;
    String status;
    String affiliateID;

    public Menu() {}

    public Menu(String foodID, String foodName, double price, String description, int calories, String promoInfo, String status, String affiliateID) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.price = price;
        this.description = description;
        this.calories = calories;
        this.promoInfo = promoInfo;
        this.status = status;
        this.affiliateID = affiliateID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getPromoInfo() {
        return promoInfo;
    }

    public void setPromoInfo(String promoInfo) {
        this.promoInfo = promoInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAffiliateID() {
        return affiliateID;
    }

    public void setAffiliateID(String affiliateID) {
        this.affiliateID = affiliateID;
    }

    @Override
    public String toString() {
        return foodID + ", " + foodName + ", " + price + ", " + description + ", " + calories + ", " + promoInfo + ", " + status + ", " + affiliateID;
    }
 
}
