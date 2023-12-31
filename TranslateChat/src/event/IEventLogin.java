/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

import model.ModelLogin;
import model.ModelMessage;
import model.ModelRegister;

/**
 *
 * @author TrongFlorida
 */
public interface IEventLogin {
    public void login(ModelLogin data);
    public void register(ModelRegister data, IEventMessage message);
    public void goRegister();
    public void goLogin();
}
