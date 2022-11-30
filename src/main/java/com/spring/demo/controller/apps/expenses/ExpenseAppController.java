package com.spring.demo.controller.apps.expenses;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.entity.Expenses;
import com.spring.demo.security.MyUserDetails;
import com.spring.demo.service.expenses.ExpenseServiceImpl;

@Controller
public class ExpenseAppController {
	
	//Global Variables
	private String year_monthly_analysis = "";
	private String month_monthly_analysis = "";
	private String year_yearly_analysis = "";
	HashMap<String, Double> monthCompareDataMap = new HashMap<>();
	HashMap<String, Double> yearCompareDataMap = new HashMap<>();
	String month_compare_blue_month_data_global="";
	String month_compare_blue_year_data_global="";
	String month_compare_red_month_data_global="";
	String month_compare_red_year_data_global="";
	String blue_year_yearly_value_global="";
	String red_year_yearly_value_global="";
	
	@Autowired
	private ExpenseServiceImpl expenseServiceimpl;
	
	@GetMapping("/apps/expensesApp")
	public ModelAndView getExpenseAppPage(@AuthenticationPrincipal MyUserDetails user) {
		ModelAndView mv = new ModelAndView();
		HashMap<String, String> UserExpenseDetails = expenseServiceimpl.getMonthlyAnalysisDetails(user.getUsername(), month_monthly_analysis, year_monthly_analysis, year_yearly_analysis);
		System.out.println("UserExpenseDetails: "+UserExpenseDetails);
		if(!UserExpenseDetails.isEmpty()) {
			Double shoppingDegreeValue = (Double.parseDouble(UserExpenseDetails.get("shoppingPercentage"))/100.0)*360.0;
			Double foodDegreeValue = (Double.parseDouble(UserExpenseDetails.get("foodPercentage"))/100.0)*360.0;
			Double travelDegreeValue = (Double.parseDouble(UserExpenseDetails.get("travelPercentage"))/100.0)*360.0;
			Double othersDegreeValue = (Double.parseDouble(UserExpenseDetails.get("otherPercentage"))/100.0)*360.0;
			mv.addObject("shoppingDegreeValue", shoppingDegreeValue);
			mv.addObject("foodDegreeValue", foodDegreeValue);
			mv.addObject("travelDegreeValue", travelDegreeValue);
			mv.addObject("othersDegreeValue", othersDegreeValue);
			
			Double shoppingYearDegreeValue = (Double.parseDouble(UserExpenseDetails.get("yearShoppingPercentage"))/100.0)*360.0;
			Double foodYearDegreeValue = (Double.parseDouble(UserExpenseDetails.get("yearFoodPercentage"))/100.0)*360.0;
			Double travelYearDegreeValue = (Double.parseDouble(UserExpenseDetails.get("yearTravelPercentage"))/100.0)*360.0;
			Double othersYearDegreeValue = (Double.parseDouble(UserExpenseDetails.get("yearOtherPercentage"))/100.0)*360.0;
			mv.addObject("shoppingYearDegreeValue", shoppingYearDegreeValue);
			mv.addObject("foodYearDegreeValue", foodYearDegreeValue);
			mv.addObject("travelYearDegreeValue", travelYearDegreeValue);
			mv.addObject("othersYearDegreeValue", othersYearDegreeValue);
			mv.addObject("year_Yearly_Analysis", UserExpenseDetails.get("year_Yearly_Analysis"));
			mv.addObject("todayYear", UserExpenseDetails.get("todayYear"));
			mv.addObject("todayMonthYear",UserExpenseDetails.get("todayYear"));
			mv.addObject("monthName", UserExpenseDetails.get("monthName"));
			mv.addObject("monthShoppingAmount", UserExpenseDetails.get("shoppingAmount"));
			mv.addObject("monthShoppingPercentage", UserExpenseDetails.get("shoppingPercentage"));
			mv.addObject("monthFoodAmount", UserExpenseDetails.get("foodAmount"));
			mv.addObject("monthFoodPercentage", UserExpenseDetails.get("foodPercentage"));
			mv.addObject("monthTravelAmount", UserExpenseDetails.get("travelAmount"));
			mv.addObject("monthTravelPercentage", UserExpenseDetails.get("travelPercentage"));
			mv.addObject("monthOtherAmount", UserExpenseDetails.get("otherAmount"));
			mv.addObject("monthOtherPercentage", UserExpenseDetails.get("otherPercentage"));
			mv.addObject("monthTotalAmount", UserExpenseDetails.get("totalAmount"));
			mv.addObject("monthTotalPercentage", UserExpenseDetails.get("totalPercentage"));
			mv.addObject("yearShoppingAmount", UserExpenseDetails.get("yearShoppingAmount"));
			mv.addObject("yearShoppingPercentage", UserExpenseDetails.get("yearShoppingPercentage"));
			mv.addObject("yearFoodAmount", UserExpenseDetails.get("yearFoodAmount"));
			mv.addObject("yearFoodPercentage", UserExpenseDetails.get("yearFoodPercentage"));
			mv.addObject("yearTravelAmount", UserExpenseDetails.get("yearTravelAmount"));
			mv.addObject("yearTravelPercentage", UserExpenseDetails.get("yearTravelPercentage"));
			mv.addObject("yearOtherAmount", UserExpenseDetails.get("yearOtherAmount"));
			mv.addObject("yearOtherPercentage", UserExpenseDetails.get("yearOtherPercentage"));
			mv.addObject("yearTotalAmount", UserExpenseDetails.get("yearTotalAmount"));
			mv.addObject("yearTotalPercentage", UserExpenseDetails.get("yearTotalPercentage"));
			mv.setViewName("Expense.html");
			return mv;
		}
		else {
			mv.setViewName("Expense.html");
			return mv;
		}
	}
	
	@PostMapping("/apps/expensesApp/addExpense")
	public String addExpense(@AuthenticationPrincipal MyUserDetails user,@RequestParam(value = "expense_type", required = false) String expense_type, @RequestParam("expense_name")
					String expense_name, @RequestParam("expense_amount") Double expense_amount,
					@RequestParam("expense_date") String expense_date) {
		Expenses expenses = new Expenses();
		expenses.setExpense_type(expense_type);
		expenses.setExpense_name(expense_name);
		expenses.setExpense_amount(expense_amount);
		expenses.setUsername(user.getUsername());
		expenses.setExpense_added_date(expense_date);
		expenseServiceimpl.add(expenses);
		return "redirect:/apps/expensesApp";
	}
	
	@GetMapping("/apps/expensesApp/expenseDetails")
	public ModelAndView showExpenseDetails(@AuthenticationPrincipal MyUserDetails user) {
		ModelAndView mv = new ModelAndView();
		List<Expenses> expenses =  expenseServiceimpl.getExpenseDetails(user.getUsername());
		mv.addObject("expenseDetails", expenses);
		for(Expenses exp:expenses) {
			System.out.println(exp.toString());
		}
		mv.setViewName("ExpenseDetails.html");
		return mv;
	}
	
	@GetMapping("/apps/expensesApp/editExpense")
	public String editExpense() {
		return "redirect:/apps/expensesApp/expenseDetails";
	}
	
	@GetMapping("/apps/expensesApp/editExpense/{id}")
	public ModelAndView editExpense(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Expenses expense = expenseServiceimpl.getExpenseDetail(id);
		mv.addObject("expense", expense);
		System.out.println(expense.toString());
		mv.setViewName("EditExpenses.html");
		return mv;
	}
	
	@PostMapping("/apps/expensesApp/editExpense/{id}")
	public String editExpensePost(@PathVariable("id") Long expense_id, @RequestParam("expense_type") String expense_type, 
			@RequestParam("expense_name") String expense_name, @RequestParam("expense_amount") Double expense_amount,
			@RequestParam("expense_date") String expense_date) {
		Expenses expense = new Expenses();
		expense.setExpense_id(expense_id);
		expense.setExpense_name(expense_name);
		expense.setExpense_type(expense_type);
		expense.setExpense_amount(expense_amount);
		expense.setExpense_added_date(expense_date);
		expenseServiceimpl.updateExpense(expense);
		return "redirect:/apps/expensesApp/expenseDetails";
	}

	@GetMapping("/apps/expensesApp/deleteExpense/{id}")
	public String deleteExpense(@PathVariable("id") Long expense_id) {
		System.out.println("expense_id: "+expense_id);
		expenseServiceimpl.deleteExpense(expense_id);
		return "redirect:/apps/expensesApp/expenseDetails";
	}
	
	@PostMapping("/apps/expensesApp/fetchDataMonthly")
	public String fetchYearlyData(@RequestParam("month_Monthly_Analysis") String month,
			@RequestParam("year_Monthly_Analysis") String year) {
		month_monthly_analysis = month;
		year_monthly_analysis = year;
		System.out.println("MONTH: "+month);
		System.out.println("YEAR: "+year);
		return "redirect:/apps/expensesApp";
	}
	
	@PostMapping("/apps/expensesApp/fetchDataYearly")
	public String fetchYearlyData(@RequestParam("year_Yearly_Analysis") String year) {
		year_yearly_analysis = year;
		System.out.println("YEAR: "+year);
		return "redirect:/apps/expensesApp";
	}
	
	@PostMapping("/apps/expensesApp/filterData")
	public ModelAndView fetchfilteredData(@AuthenticationPrincipal MyUserDetails user,
			@RequestParam("expense_type") String expense_type,
			@RequestParam("expense_name") String expense_name,
			@RequestParam(value="expense_amount") String expense_amount,
			@RequestParam("expense_date") String expense_date) {
		ModelAndView mv = new ModelAndView();
		Double d;
		if(!expense_amount.isEmpty()) {
			System.out.println("expense_amount: "+expense_amount);
			d = new Double(expense_amount);
		}else {
			d = 0.0;
		}
		try {
		System.out.println("expense_type: "+expense_type);
		System.out.println("expense_name: "+expense_name);
		System.out.println("expense_amount: "+expense_amount);
		System.out.println("expense_date: "+expense_date);
		}catch(Exception e) {
			System.out.println(e);
		}
		List<Expenses> expenses =  expenseServiceimpl.getExpenseDetails(user.getUsername(),expense_type,expense_name,d,expense_date);
		mv.addObject("expenseDetails", expenses);
		for(Expenses exp:expenses) {
			System.out.println(exp.toString());
		}
		mv.setViewName("ExpenseDetails.html");
		return mv;
	}
	
	@GetMapping("/apps/expensesApp/getCompareExpenses")
	public ModelAndView getCompareExpenses() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("month_shopping_blue_percentage", 0);
		mv.addObject("month_food_blue_percentage", 0);
		mv.addObject("month_travel_blue_percentage", 0);
		mv.addObject("month_others_blue_percentage", 0);
		mv.addObject("month_shopping_red_percentage", 0);
		mv.addObject("month_food_red_percentage", 0);
		mv.addObject("month_travel_red_percentage", 0);
		mv.addObject("month_others_red_percentage", 0);
		
		mv.addObject("year_shopping_blue_percentage", 0);
		mv.addObject("year_food_blue_percentage", 0);
		mv.addObject("year_travel_blue_percentage", 0);
		mv.addObject("year_others_blue_percentage", 0);
		mv.addObject("year_shopping_red_percentage", 0);
		mv.addObject("year_food_red_percentage", 0);
		mv.addObject("year_travel_red_percentage", 0);
		mv.addObject("year_others_red_percentage", 0);
		mv.setViewName("CompareExpenses.html");
		return mv;
	}
	@PostMapping("/apps/expensesApp/compareMonthly")
	public ModelAndView compareMonthlyExpenses(@AuthenticationPrincipal MyUserDetails user,
			@RequestParam("month_compare_blue_month_data") String month_compare_blue_month_data,
			@RequestParam("month_compare_blue_year_data") String month_compare_blue_year_data,
			@RequestParam("month_compare_red_month_data") String month_compare_red_month_data,
			@RequestParam("month_compare_red_year_data") String month_compare_red_year_data) {
		ModelAndView mv = new ModelAndView();
		month_compare_blue_month_data_global = month_compare_blue_month_data;
		month_compare_blue_year_data_global = month_compare_blue_year_data;
		month_compare_red_month_data_global = month_compare_red_month_data;
		month_compare_red_year_data_global = month_compare_red_year_data;
		monthCompareDataMap = expenseServiceimpl.getCompareMonthlyExpense(
				user.getUsername(),
				month_compare_blue_month_data, month_compare_blue_year_data,
				month_compare_red_month_data, month_compare_red_year_data);
		mv.addObject("blueMonthlyMonthValue", month_compare_blue_month_data_global);
		mv.addObject("blueMonthlyYearValue", month_compare_blue_year_data_global);
		mv.addObject("redMonthlyMonthValue", month_compare_red_month_data_global);
		mv.addObject("redMonthlyYearValue", month_compare_red_year_data_global);
		mv.addObject("month_shopping_blue_percentage", monthCompareDataMap.get("monthBlueshoppingPercentage"));
		mv.addObject("month_food_blue_percentage", monthCompareDataMap.get("monthBluefoodPercentage"));
		mv.addObject("month_travel_blue_percentage", monthCompareDataMap.get("monthBluetravelPercentage"));
		mv.addObject("month_others_blue_percentage", monthCompareDataMap.get("monthBlueotherPercentage"));
		mv.addObject("month_shopping_red_percentage", monthCompareDataMap.get("monthRedshoppingPercentage"));
		mv.addObject("month_food_red_percentage", monthCompareDataMap.get("monthRedfoodPercentage"));
		mv.addObject("month_travel_red_percentage", monthCompareDataMap.get("monthRedtravelPercentage"));
		mv.addObject("month_others_red_percentage", monthCompareDataMap.get("monthRedotherPercentage"));
		
		if(yearCompareDataMap==null || yearCompareDataMap.isEmpty()) {
			yearCompareDataMap.put("yearBlueshoppingPercentage", 0.0);
			yearCompareDataMap.put("yearBluefoodPercentage", 0.0);
			yearCompareDataMap.put("yearBluetravelPercentage", 0.0);
			yearCompareDataMap.put("yearBlueotherPercentage", 0.0);
			yearCompareDataMap.put("yearRedshoppingPercentage", 0.0);
			yearCompareDataMap.put("yearRedfoodPercentage", 0.0);
			yearCompareDataMap.put("yearRedtravelPercentage", 0.0);
			yearCompareDataMap.put("yearRedotherPercentage", 0.0);
		}
		

		mv.addObject("year_shopping_blue_percentage", yearCompareDataMap.get("yearBlueshoppingPercentage"));
		mv.addObject("year_food_blue_percentage", yearCompareDataMap.get("yearBluefoodPercentage"));
		mv.addObject("year_travel_blue_percentage", yearCompareDataMap.get("yearBluetravelPercentage"));
		mv.addObject("year_others_blue_percentage", yearCompareDataMap.get("yearBlueotherPercentage"));
		mv.addObject("year_shopping_red_percentage", yearCompareDataMap.get("yearRedshoppingPercentage"));
		mv.addObject("year_food_red_percentage", yearCompareDataMap.get("yearRedfoodPercentage"));
		mv.addObject("year_travel_red_percentage", yearCompareDataMap.get("yearRedtravelPercentage"));
		mv.addObject("year_others_red_percentage", yearCompareDataMap.get("yearRedotherPercentage"));

		mv.addObject("blue_year_yearly_value", blue_year_yearly_value_global);
		mv.addObject("red_year_yearly_value", red_year_yearly_value_global);
		
		mv.setViewName("CompareExpenses.html");
		return mv;
	}
	
	@PostMapping("/apps/expensesApp/compareYearly")
	public ModelAndView compareYearlyExpenses(@AuthenticationPrincipal MyUserDetails user,
			@RequestParam("blueYearlyYearValue") String blueYearlyYearValue,
			@RequestParam("redYearlyYearValue") String redYearlyYearValue) {
		ModelAndView mv = new ModelAndView();
		blue_year_yearly_value_global = blueYearlyYearValue;
		red_year_yearly_value_global = redYearlyYearValue;
		yearCompareDataMap = expenseServiceimpl.getCompareYearlyExpense(
				user.getUsername(),
				blueYearlyYearValue, redYearlyYearValue);
		mv.addObject("blueMonthlyMonthValue", month_compare_blue_month_data_global);
		mv.addObject("blueMonthlyYearValue", month_compare_blue_year_data_global);
		mv.addObject("redMonthlyMonthValue", month_compare_red_month_data_global);
		mv.addObject("redMonthlyYearValue", month_compare_red_year_data_global);
		
		if(monthCompareDataMap==null || monthCompareDataMap.isEmpty()) {
			monthCompareDataMap.put("monthBlueshoppingPercentage", 0.0);
			monthCompareDataMap.put("monthBluefoodPercentage", 0.0);
			monthCompareDataMap.put("monthBluetravelPercentage", 0.0);
			monthCompareDataMap.put("monthBlueotherPercentage", 0.0);
			monthCompareDataMap.put("monthRedshoppingPercentage", 0.0);
			monthCompareDataMap.put("monthRedfoodPercentage", 0.0);
			monthCompareDataMap.put("monthRedtravelPercentage", 0.0);
			monthCompareDataMap.put("monthRedotherPercentage", 0.0);
		}
		
		mv.addObject("month_shopping_blue_percentage", monthCompareDataMap.get("monthBlueshoppingPercentage"));
		mv.addObject("month_food_blue_percentage", monthCompareDataMap.get("monthBluefoodPercentage"));
		mv.addObject("month_travel_blue_percentage", monthCompareDataMap.get("monthBluetravelPercentage"));
		mv.addObject("month_others_blue_percentage", monthCompareDataMap.get("monthBlueotherPercentage"));
		mv.addObject("month_shopping_red_percentage", monthCompareDataMap.get("monthRedshoppingPercentage"));
		mv.addObject("month_food_red_percentage", monthCompareDataMap.get("monthRedfoodPercentage"));
		mv.addObject("month_travel_red_percentage", monthCompareDataMap.get("monthRedtravelPercentage"));
		mv.addObject("month_others_red_percentage", monthCompareDataMap.get("monthRedotherPercentage"));
		
		mv.addObject("year_shopping_blue_percentage", yearCompareDataMap.get("yearBlueshoppingPercentage"));
		mv.addObject("year_food_blue_percentage", yearCompareDataMap.get("yearBluefoodPercentage"));
		mv.addObject("year_travel_blue_percentage", yearCompareDataMap.get("yearBluetravelPercentage"));
		mv.addObject("year_others_blue_percentage", yearCompareDataMap.get("yearBlueotherPercentage"));
		mv.addObject("year_shopping_red_percentage", yearCompareDataMap.get("yearRedshoppingPercentage"));
		mv.addObject("year_food_red_percentage", yearCompareDataMap.get("yearRedfoodPercentage"));
		mv.addObject("year_travel_red_percentage", yearCompareDataMap.get("yearRedtravelPercentage"));
		mv.addObject("year_others_red_percentage", yearCompareDataMap.get("yearRedotherPercentage"));
		
		mv.addObject("blue_year_yearly_value", blue_year_yearly_value_global);
		mv.addObject("red_year_yearly_value", red_year_yearly_value_global);
		mv.setViewName("CompareExpenses.html");
		return mv;
	}
	
}
