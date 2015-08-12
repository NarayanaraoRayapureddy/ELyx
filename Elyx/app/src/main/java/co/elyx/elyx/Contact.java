package co.elyx.elyx;


public class Contact {

    int id;
    String name;
    String phoneNumber;
    String photoThumbnail;

    public Contact(){

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoThumbnail() {
        return this.photoThumbnail;
    }

    public void setPhotoThumbnail(String photoThumbnail) {
        this.photoThumbnail = photoThumbnail;
    }

    public Contact(int id, String name, String phoneNumber, String photoThumbnail) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.photoThumbnail = photoThumbnail;
    }

    public Contact(String name, String phoneNumber, String photoThumbnail) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.photoThumbnail = photoThumbnail;
    }
}

