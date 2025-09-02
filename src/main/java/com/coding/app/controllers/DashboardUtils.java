package com.coding.app.controllers;

import org.springframework.web.servlet.ModelAndView;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DashboardUtils {

	public static void activateMenu(final NavbarMenu menu, final ModelAndView modelAndView) {
		modelAndView.addObject(menu.getCssClassName(), "mm-active");
	}

	@AllArgsConstructor
	@Getter
	public enum NavbarMenu {

		DASHBOARD("dashboardActive"),
		CAR("carActive"),
		RESERVATIONS("reservationActive"),
		MANAGERS("managerActive"),
		CLIENTS("clientActive"),
		HISTORY("historyActive");

		private final String cssClassName;
	}
}
