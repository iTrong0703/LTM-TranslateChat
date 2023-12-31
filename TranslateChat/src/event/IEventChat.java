/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

import java.util.List;
import model.ModelReceiveMessage;
import model.ModelSendMessage;


public interface IEventChat {
    public void sendMessage(ModelSendMessage data);
    public void receiveMessage(ModelReceiveMessage data);
    public void displayChatHistory(List<ModelReceiveMessage> chatHistory);
    public void translateChatHistory(String sourceLang, String targetLang);
    public void originalChatHistory();
    public void defaultLanguage(String selectedSourceLanguage, String selectedTargetLanguage);
}
