
package event;

import model.ModelUserAccount;

public interface IEventMain {
    public void showLoading(boolean show);
    public void initChat();
    public void selectUser(ModelUserAccount user);
    public void updateUser(ModelUserAccount user);
}
