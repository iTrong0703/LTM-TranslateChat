
package event;

//Hiện ảnh to lên khi ấn vào
public class PublicEvent {
    private static PublicEvent instance; //Tạo biến static tên là instance, khởi tạo cùng chương trình, truy cập thông qua tên class
    private IEventMain eventMain;
    private IEventImageView eventImgView; // eventImgView dùng làm tham chiếu tới Interface
    private IEventChat eventChat;
    private IEventLogin eventLogin;
    private IEventMenuLeft eventMenuLeft;
    private IEventMenuRight eventMenuRight; 
    
    public static PublicEvent getInstance() {
        if(instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }
    
    private PublicEvent() {
        
    }
    
    public void addEventMain(IEventMain evt) {
        this.eventMain = evt;
    }
    
    public void addEventImageView(IEventImageView evt) {// Thêm implements vào biến eventImgView
        this.eventImgView = evt;
    }
    
    public void addEventChat(IEventChat evt) {
        this.eventChat = evt;
    }
    
    public void addEventLogin(IEventLogin evt) {
        this.eventLogin = evt;
    }
    
    public void addEventMenuLeft(IEventMenuLeft evt) {
        this.eventMenuLeft = evt;
    }
    
    public void addEventMenuRight(IEventMenuRight evt) { // New method to add IEventMenuRight
        this.eventMenuRight = evt;
    }
    
    public IEventMain getEventMain() {
        return eventMain;
    }
    
    public IEventImageView geEventImageView() {// Trả về đối tượng implements
        return eventImgView;
    }
    
    public IEventChat getEventChat() {
        return eventChat;
    }
    
    public IEventLogin getEventLogin() {
        return eventLogin;
    }
    
    public IEventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }
    
    public IEventMenuRight getEventMenuRight() { // New method to get IEventMenuRight
        return eventMenuRight;
    }
}
