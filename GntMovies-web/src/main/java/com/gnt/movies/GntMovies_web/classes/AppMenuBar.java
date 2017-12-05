package com.gnt.movies.GntMovies_web.classes;

import com.vaadin.ui.MenuBar;

@SuppressWarnings("serial")
public class AppMenuBar extends MenuBar {
	
	public AppMenuBar() {
		this.addItem(Utils.HOME_MENU_BAR, null, null).setCommand(new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				
			}
		});
		this.addItem(Utils.UPCOMING_MENU_BAR, null, null).setCommand(new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				
			}
		});
		this.addItem(Utils.NOW_PLAYING_MENU_BAR, null, null).setCommand(new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				
			}
		});
		this.addItem(Utils.ON_THE_AIR_MENU_BAR, null, null).setCommand(new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				
			}
		});
		this.addItem(Utils.AIR2DAY_SHOWS,null,null).setCommand(new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
