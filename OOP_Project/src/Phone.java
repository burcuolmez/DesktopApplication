package src;
public class Phone {
	private String cityCode;
    private String number;
    public Phone(String cityCode, String number) {
        this.cityCode = cityCode;
        this.number = number;
    }
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return cityCode + number;
    }
}
