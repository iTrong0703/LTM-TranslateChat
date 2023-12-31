/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

import java.util.List;
import model.ModelUserAccount;

/**
 *
 * @author TrongFlorida
 */
public interface IEventMenuLeft {
    public void newUser(List<ModelUserAccount> users);
    public void userConnect(int userID);
    public void userDisconnect(int userID);
    public void userLoggedIn(int userID);
}
