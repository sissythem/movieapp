package com.gnt.movies.GntMovies_web.UI.windows;

import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class Message extends Window implements ClickListener{
	public enum MessageType {
        INFO, WARN, ERROR, SUCCESS
    }
    
    private static final ThemeResource ERROR_ICON = new ThemeResource(Utilities.ERROR_ICON_PATH);
    private static final ThemeResource INFO_ICON = new ThemeResource(Utilities.INFO_ICON_PATH);
    private static final ThemeResource WARNING_ICON = new ThemeResource(Utilities.WARN_ICON_PATH);
    private static final ThemeResource SUCCESS_ICON = new ThemeResource(Utilities.SUCCESS_ICON_PATH);
    
    public Message(String caption, String message, MessageType messageType){
        setCaption(caption);
        HorizontalLayout iconAndMessage = createMessageLayout(message, messageType);
        VerticalLayout vLayout = createWindowContent(iconAndMessage);
        this.setContent(vLayout);
        this.setResizable(false);
        this.setSizeUndefined();
        this.setModal(true);
    }
    
    public static Image initImageIcon(MessageType messageType) {
    	Image icon = new Image();
        switch (messageType) {
        case INFO:
            icon.setIcon(INFO_ICON);
            break;
        case WARN:
            icon.setIcon(WARNING_ICON);
            break;
        case ERROR:
            icon.setIcon(ERROR_ICON);
            break;
        case SUCCESS:
        	icon.setIcon(SUCCESS_ICON);
        }
        return icon;
    }
    
    private VerticalLayout createWindowContent(HorizontalLayout iconAndMessage) {
    	VerticalLayout vLayout = new VerticalLayout();
    	 Button okBtn = new Button(Utilities.OK_BUTTON, this);
    	 vLayout.setMargin(true);
         vLayout.setSpacing(true);
         vLayout.addComponents(iconAndMessage, okBtn);
         vLayout.setComponentAlignment(okBtn, Alignment.MIDDLE_CENTER);
         return vLayout;
    }
    
    private HorizontalLayout createMessageLayout(String message, MessageType messageType) {
    	HorizontalLayout iconAndMessage = new HorizontalLayout();
    	Image icon = initImageIcon(messageType);
        Label messageLbl = new Label(message, ContentMode.HTML);
        iconAndMessage.setSpacing(true);
        iconAndMessage.addComponents(icon, messageLbl);
        iconAndMessage.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
        iconAndMessage.setComponentAlignment(messageLbl, Alignment.MIDDLE_CENTER);
        iconAndMessage.setExpandRatio(messageLbl, 1);
        return iconAndMessage;
    }
    
    public static void show(String caption, String message, MessageType messageType){
        Message md = new Message(caption, message, messageType);
        UI.getCurrent().addWindow(md);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        this.close();
    }
}
