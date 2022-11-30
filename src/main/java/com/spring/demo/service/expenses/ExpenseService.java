package com.spring.demo.service.expenses;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters.LocalDateTimeToDateConverter;
import org.springframework.stereotype.Service;

import com.spring.demo.dao.expenses.ExpensesDao;
import com.spring.demo.entity.Expenses;

@Service
public class ExpenseService implements ExpenseServiceImpl{
	
	@Autowired
	private ExpensesDao expenseDao;
	
	String[] months_list = new String[] {"JANUARY","FEBRAUARY","MARCH","APRIL","MAY","JUNE","JULY",
			"AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
	
	@Override
	public void add(Expenses expenses) {
		LocalDate todayDate = LocalDate.now();
		if(expenses.getExpense_added_date().isEmpty()) {
			expenses.setExpense_added_date(todayDate.toString());
		}
		System.out.println(expenses.toString());
		expenseDao.addExpense(expenses);
	}

	@Override
	public HashMap<String, String> getMonthlyAnalysisDetails(String username, String month, String year, String year_yearly) {
		HashMap<String, String> UserExpenseDetails = new HashMap<>();
		LocalDate todayDate = LocalDate.now();
		Month todayMonthName;
		System.out.println("month: "+month);
		System.out.println("year: "+year);
		int yearYearly = 0;
		int todayYear = 0;
		int todayMonth = 0;
		if(month.equals("") || month.isEmpty()) {
			todayMonth = todayDate.getMonthValue();
			todayMonthName = todayDate.getMonth();
			UserExpenseDetails.put("monthName", todayMonthName.toString());
		}
		else {
			for(int i=0;i<=months_list.length;i++) {
				if(months_list[i].equals(month)) {
					todayMonth = i+1;
					break;
				}
			}
			UserExpenseDetails.put("monthName", month);
		}
		if(year.isEmpty() || year.equals("")) {
			todayYear = todayDate.getYear();
		}else {
			todayYear = Integer.parseInt(year);
		}
		if(year_yearly.isEmpty() || year_yearly.equals("")) {
			yearYearly = todayDate.getYear();
		}
		else {
			yearYearly = Integer.parseInt(year_yearly);
		}
		List<Expenses> expenses= expenseDao.getMonthlyAnalysisDetails(username);
		Double shoppingAmount = 0.0;
		float shoppingPercentage = 0f;
		Double foodAmount = 0.0;
		float foodPercentage = 0f;
		Double travelAmount = 0.0;
		float travelPercentage = 0f;
		Double otherAmount = 0.0;
		float otherPercentage = 0f;
		Double totalAmount = 0.0;
		float totalPercentage = 0f;
		
		Double yearShoppingAmount = 0.0;
		float yearShoppingPercentage = 0f;
		Double yearFoodAmount = 0.0;
		float yearFoodPercentage = 0f;
		Double yearTravelAmount = 0.0;
		float yearTravelPercentage = 0f;
		Double yearOtherAmount = 0.0;
		float yearOtherPercentage = 0f;
		Double yearTotalAmount = 0.0;
		float yearTotalPercentage = 0f;
		
		System.out.println("todayYear:"+todayYear);
		System.out.println("todayMonth:"+todayMonth);
		if(!expenses.isEmpty()) {
			for(Expenses exp:expenses) {
				LocalDate expense_added_date = LocalDate.parse(exp.getExpense_added_date());
				int expense_added_month_value = expense_added_date.getMonthValue();
				int expense_added_year_value = expense_added_date.getYear();
				if(todayYear == expense_added_year_value) {
					
//					if(exp.getExpense_type().equals("Shopping"))
//						yearShoppingAmount = yearShoppingAmount + exp.getExpense_amount();
//					if(exp.getExpense_type().equals("Food"))
//						yearFoodAmount = yearFoodAmount + exp.getExpense_amount();
//					if(exp.getExpense_type().equals("Travel"))
//						yearTravelAmount = yearTravelAmount + exp.getExpense_amount();
//					if(exp.getExpense_type().equals("Others"))
//						yearOtherAmount = yearOtherAmount + exp.getExpense_amount();
//					yearTotalAmount = yearTotalAmount + exp.getExpense_amount();
					
					
					if(todayMonth == expense_added_month_value) {
						if(exp.getExpense_type().equals("Shopping"))
							shoppingAmount = shoppingAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Food"))
							foodAmount = foodAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Travel"))
							travelAmount = travelAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Others"))
							otherAmount = otherAmount + exp.getExpense_amount();
						totalAmount = totalAmount + exp.getExpense_amount();
					}
				}
				
				if(yearYearly == expense_added_year_value) {
					if(exp.getExpense_type().equals("Shopping"))
						yearShoppingAmount = yearShoppingAmount + exp.getExpense_amount();
					if(exp.getExpense_type().equals("Food"))
						yearFoodAmount = yearFoodAmount + exp.getExpense_amount();
					if(exp.getExpense_type().equals("Travel"))
						yearTravelAmount = yearTravelAmount + exp.getExpense_amount();
					if(exp.getExpense_type().equals("Others"))
						yearOtherAmount = yearOtherAmount + exp.getExpense_amount();
						yearTotalAmount = yearTotalAmount + exp.getExpense_amount();
				}
			}
		}
		
		if(totalAmount>0) {
			shoppingPercentage = Math.round((float) ((shoppingAmount/totalAmount)*100));
			foodPercentage = Math.round((float) ((foodAmount/totalAmount)*100));
			travelPercentage = Math.round((float) ((travelAmount/totalAmount)*100));
			otherPercentage = Math.round((float) ((otherAmount/totalAmount)*100));
			totalPercentage = Math.round((float) ((totalAmount/totalAmount)*100));
		}
		
		if(yearTotalAmount>0) {
			yearShoppingPercentage = Math.round((float) ((yearShoppingAmount/yearTotalAmount)*100));
			yearFoodPercentage = Math.round((float) ((yearFoodAmount/yearTotalAmount)*100));
			yearTravelPercentage = Math.round((float) ((yearTravelAmount/yearTotalAmount)*100));
			yearOtherPercentage = Math.round((float) ((yearOtherAmount/yearTotalAmount)*100));
			yearTotalPercentage = Math.round((float) ((yearTotalAmount/yearTotalAmount)*100));
		}

		UserExpenseDetails.put("year_Yearly_Analysis", Integer.toString(yearYearly));
		UserExpenseDetails.put("todayYear", Integer.toString(todayYear));
		UserExpenseDetails.put("shoppingAmount", new BigDecimal(shoppingAmount).toPlainString());
		UserExpenseDetails.put("shoppingPercentage", new BigDecimal(shoppingPercentage).toPlainString());
		UserExpenseDetails.put("foodAmount", new BigDecimal(foodAmount).toPlainString());
		UserExpenseDetails.put("foodPercentage", new BigDecimal(foodPercentage).toPlainString());
		UserExpenseDetails.put("travelAmount", new BigDecimal(travelAmount).toPlainString());
		UserExpenseDetails.put("travelPercentage", new BigDecimal(travelPercentage).toPlainString());
		UserExpenseDetails.put("otherAmount", new BigDecimal(otherAmount).toPlainString());
		UserExpenseDetails.put("otherPercentage", new BigDecimal(otherPercentage).toPlainString());
		UserExpenseDetails.put("totalAmount", new BigDecimal(totalAmount).toPlainString());
		UserExpenseDetails.put("totalPercentage", new BigDecimal(totalPercentage).toPlainString());
		UserExpenseDetails.put("yearShoppingAmount", new BigDecimal(yearShoppingAmount).toPlainString());
		UserExpenseDetails.put("yearShoppingPercentage", new BigDecimal(yearShoppingPercentage).toPlainString());
		UserExpenseDetails.put("yearFoodAmount", new BigDecimal(yearFoodAmount).toPlainString());
		UserExpenseDetails.put("yearFoodPercentage", new BigDecimal(yearFoodPercentage).toPlainString());
		UserExpenseDetails.put("yearTravelAmount", new BigDecimal(yearTravelAmount).toPlainString());
		UserExpenseDetails.put("yearTravelPercentage", new BigDecimal(yearTravelPercentage).toPlainString());
		UserExpenseDetails.put("yearOtherAmount", new BigDecimal(yearOtherAmount).toPlainString());
		UserExpenseDetails.put("yearOtherPercentage", new BigDecimal(yearOtherPercentage).toPlainString());
		UserExpenseDetails.put("yearTotalAmount", new BigDecimal(yearTotalAmount).toPlainString());
		UserExpenseDetails.put("yearTotalPercentage", new BigDecimal(yearTotalPercentage).toPlainString());
		System.out.println("UserExpenseDetails: "+UserExpenseDetails);
		return UserExpenseDetails;
	}

	@Override
	public List<Expenses> getExpenseDetails(String username, String expense_type, String expense_name, Double expense_amount, String expense_date) {
		List<Expenses> expenses = new ArrayList<>();
		System.out.println("Inside getExpenseDetails");
		if(expense_type.isEmpty() && expense_name.isEmpty() && expense_amount==0.0 && expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetails(username);
		}
		else if(!expense_type.isEmpty() && expense_name.isEmpty() && expense_amount==0.0 && expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseType(username, expense_type);
		}
		else if(expense_type.isEmpty() && !expense_name.isEmpty() && expense_amount==0.0 && expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseName(username, expense_name);
		}
		else if(expense_type.isEmpty() && expense_name.isEmpty() && expense_amount!=0.0 && expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseAmount(username, expense_amount);
		}
		else if(expense_type.isEmpty() && expense_name.isEmpty() && expense_amount==0.0 && !expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseDate(username, expense_date);
		}
		else if(!expense_type.isEmpty() && !expense_name.isEmpty() && expense_amount==0.0 && expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseTypeAndExpenseName(username, expense_type, expense_name);
		}
		else if(!expense_type.isEmpty() && expense_name.isEmpty() && expense_amount!=0.0 && expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseTypeAndExpenseAmount(username, expense_type, expense_amount);
		}
		else if(!expense_type.isEmpty() && expense_name.isEmpty() && expense_amount==0.0 && !expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseTypeAndExpenseDate(username, expense_type, expense_date);
		}
		else if(expense_type.isEmpty() && !expense_name.isEmpty() && expense_amount!=0.0 && expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseNameAndExpenseAmount(username, expense_name, expense_amount);
		}
		else if(expense_type.isEmpty() && !expense_name.isEmpty() && expense_amount==0.0 && !expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseNameAndExpenseDate(username, expense_name, expense_date);
		}
		else if(expense_type.isEmpty() && expense_name.isEmpty() && expense_amount!=0.0 && !expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseAmountAndExpenseDate(username, expense_amount, expense_date);
		}
		else if(!expense_type.isEmpty() && !expense_name.isEmpty() && expense_amount!=0.0 && expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseAmount(username, expense_type, expense_name, expense_amount);
		}
		else if(!expense_type.isEmpty() && !expense_name.isEmpty() && expense_amount==0.0 && !expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseDate(username, expense_type, expense_name, expense_date);
		}
		else if(!expense_type.isEmpty() && expense_name.isEmpty() && expense_amount!=0.0 && !expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseTypeAndExpenseAmountAndExpenseAmount(username, expense_type, expense_amount, expense_date);
		}
		else if(expense_type.isEmpty() && !expense_name.isEmpty() && expense_amount!=0.0 && !expense_date.isEmpty()) {
			expenses = expenseDao.getExpenseDetailsExpenseNameAndExpenseAmountAndExpenseDate(username, expense_name, expense_amount, expense_date);
		}
		else {
			expenses = expenseDao.getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseAmountAndExpenseDate(username,expense_type, expense_name, expense_amount, expense_date);
		}
		return expenses;
	}

	@Override
	public void deleteExpense(Long expense_id) {
		expenseDao.deleteExpense(expense_id);
	}

	@Override
	public Expenses getExpenseDetail(Long id) {
		// TODO Auto-generated method stub
		return expenseDao.getExpenseDetails(id);
	}

	@Override
	public void updateExpense(Expenses expense) {
		expenseDao.updateExpense(expense);
	}

	@Override
	public List<Expenses> getExpenseDetails(String username) {
		return expenseDao.getExpenseDetails(username);
	}

	@Override
	public HashMap<String, Double> getCompareMonthlyExpense(String username,String month_compare_blue_month_data,
			String month_compare_blue_year_data, String month_compare_red_month_data,
			String month_compare_red_year_data) {
		HashMap<String, Double> monthlyCompareDetails = new HashMap<>();
		System.out.println("month_compare_blue_month_data: "+month_compare_blue_month_data);
		System.out.println("month_compare_blue_year_data: "+month_compare_blue_year_data);
		System.out.println("month_compare_red_month_data: "+month_compare_red_month_data);
		System.out.println("month_compare_red_year_data: "+month_compare_red_year_data);
			int blueMonthValue = 0;
			int redMonthValue = 0;
			int blueYearValue = Integer.parseInt(month_compare_blue_year_data);
			int redYearValue = Integer.parseInt(month_compare_red_year_data);
			for(int i=0;i<=months_list.length;i++) {
				if(months_list[i].equals(month_compare_blue_month_data)) {
					blueMonthValue = i+1;
					break;
				}
			}
			for(int i=0;i<=months_list.length;i++) {
				if(months_list[i].equals(month_compare_red_month_data)) {
					redMonthValue = i+1;
					break;
				}
			}
			System.out.println("blueMonthValue: "+blueMonthValue);
			System.out.println("redMonthValue: "+redMonthValue);
		List<Expenses> expenses= expenseDao.getMonthlyAnalysisDetails(username);
		Double monthBlueshoppingAmount = 0.0;
		float monthBlueshoppingPercentage = 0f;
		Double monthBluefoodAmount = 0.0;
		float monthBluefoodPercentage = 0f;
		Double monthBluetravelAmount = 0.0;
		float monthBluetravelPercentage = 0f;
		Double monthBlueotherAmount = 0.0;
		float monthBlueotherPercentage = 0f;
		Double monthBluetotalAmount = 0.0;
		float monthBluetotalPercentage = 0f;

		Double monthRedshoppingAmount = 0.0;
		float monthRedshoppingPercentage = 0f;
		Double monthRedfoodAmount = 0.0;
		float monthRedfoodPercentage = 0f;
		Double monthRedtravelAmount = 0.0;
		float monthRedtravelPercentage = 0f;
		Double monthRedotherAmount = 0.0;
		float monthRedotherPercentage = 0f;
		Double monthRedtotalAmount = 0.0;
		float monthRedtotalPercentage = 0f;
		
		if(!expenses.isEmpty()) {
			for(Expenses exp:expenses) {
				LocalDate expense_added_date = LocalDate.parse(exp.getExpense_added_date());
				int expense_added_month_value = expense_added_date.getMonthValue();
				int expense_added_year_value = expense_added_date.getYear();
				
				//blue
				if(blueYearValue == expense_added_year_value) {
					
//					if(exp.getExpense_type().equals("Shopping"))
//						yearShoppingAmount = yearShoppingAmount + exp.getExpense_amount();
//					if(exp.getExpense_type().equals("Food"))
//						yearFoodAmount = yearFoodAmount + exp.getExpense_amount();
//					if(exp.getExpense_type().equals("Travel"))
//						yearTravelAmount = yearTravelAmount + exp.getExpense_amount();
//					if(exp.getExpense_type().equals("Others"))
//						yearOtherAmount = yearOtherAmount + exp.getExpense_amount();
//					yearTotalAmount = yearTotalAmount + exp.getExpense_amount();
					
					
					if(blueMonthValue == expense_added_month_value) {
						if(exp.getExpense_type().equals("Shopping"))
							monthBlueshoppingAmount = monthBlueshoppingAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Food"))
							monthBluefoodAmount = monthBluefoodAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Travel"))
							monthBluetravelAmount = monthBluetravelAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Others"))
							monthBlueotherAmount = monthBlueotherAmount + exp.getExpense_amount();
						monthBluetotalAmount = monthBluetotalAmount + exp.getExpense_amount();
					}
				}
				
				//red
					if(redYearValue == expense_added_year_value) {
										
					//					if(exp.getExpense_type().equals("Shopping"))
					//						yearShoppingAmount = yearShoppingAmount + exp.getExpense_amount();
					//					if(exp.getExpense_type().equals("Food"))
					//						yearFoodAmount = yearFoodAmount + exp.getExpense_amount();
					//					if(exp.getExpense_type().equals("Travel"))
					//						yearTravelAmount = yearTravelAmount + exp.getExpense_amount();
					//					if(exp.getExpense_type().equals("Others"))
					//						yearOtherAmount = yearOtherAmount + exp.getExpense_amount();
					//					yearTotalAmount = yearTotalAmount + exp.getExpense_amount();
					
					
					if(redMonthValue == expense_added_month_value) {
						if(exp.getExpense_type().equals("Shopping"))
							monthRedshoppingAmount = monthRedshoppingAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Food"))
							monthRedfoodAmount = monthRedfoodAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Travel"))
							monthRedtravelAmount = monthRedtravelAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Others"))
							monthRedotherAmount = monthRedotherAmount + exp.getExpense_amount();
						monthRedtotalAmount = monthRedtotalAmount + exp.getExpense_amount();
					}
				}
				
			}
		}
		
		if(monthBluetotalAmount>0) {
			monthBlueshoppingPercentage = Math.round((float) ((monthBlueshoppingAmount/monthBluetotalAmount)*100));
			monthBluefoodPercentage = Math.round((float) ((monthBluefoodAmount/monthBluetotalAmount)*100));
			monthBluetravelPercentage = Math.round((float) ((monthBluetravelAmount/monthBluetotalAmount)*100));
			monthBlueotherPercentage = Math.round((float) ((monthBlueotherAmount/monthBluetotalAmount)*100));
			monthBluetotalPercentage = Math.round((float) ((monthBluetotalAmount/monthBluetotalAmount)*100));
		}
		
		if(monthRedtotalAmount>0) {
			monthRedshoppingPercentage = Math.round((float) ((monthRedshoppingAmount/monthRedtotalAmount)*100));
			monthRedfoodPercentage = Math.round((float) ((monthRedfoodAmount/monthRedtotalAmount)*100));
			monthRedtravelPercentage = Math.round((float) ((monthRedtravelAmount/monthRedtotalAmount)*100));
			monthRedotherPercentage = Math.round((float) ((monthRedotherAmount/monthRedtotalAmount)*100));
			monthRedtotalPercentage = Math.round((float) ((monthRedtotalAmount/monthRedtotalAmount)*100));
		}
		
		monthlyCompareDetails.put("monthBlueshoppingPercentage", (double)monthBlueshoppingPercentage);
		monthlyCompareDetails.put("monthBluefoodPercentage", (double)monthBluefoodPercentage);
		monthlyCompareDetails.put("monthBluetravelPercentage", (double)monthBluetravelPercentage);
		monthlyCompareDetails.put("monthBlueotherPercentage", (double)monthBlueotherPercentage);
		monthlyCompareDetails.put("monthRedshoppingPercentage", (double)monthRedshoppingPercentage);
		monthlyCompareDetails.put("monthRedfoodPercentage", (double)monthRedfoodPercentage);
		monthlyCompareDetails.put("monthRedtravelPercentage", (double)monthRedtravelPercentage);
		monthlyCompareDetails.put("monthRedotherPercentage", (double)monthRedotherPercentage);
		System.out.println("monthlyCompareDetails: "+monthlyCompareDetails);
		return monthlyCompareDetails;
	}

	@Override
	public HashMap<String, Double> getCompareYearlyExpense(String username, String blueYearlyYearValue,
			String redYearlyYearValue) {
		HashMap<String, Double> yearlyCompareDetails = new HashMap<>();
			int blueYearValue = Integer.parseInt(blueYearlyYearValue);
			int redYearValue = Integer.parseInt(redYearlyYearValue);
		List<Expenses> expenses= expenseDao.getMonthlyAnalysisDetails(username);
		Double yearBlueShoppingAmount = 0.0;
		float yearBlueShoppingPercentage = 0f;
		Double yearBlueFoodAmount = 0.0;
		float yearBlueFoodPercentage = 0f;
		Double yearBlueTravelAmount = 0.0;
		float yearBlueTravelPercentage = 0f;
		Double yearBlueOtherAmount = 0.0;
		float yearBlueOtherPercentage = 0f;
		Double yearBlueTotalAmount = 0.0;
		float yearBlueTotalPercentage = 0f;

		Double yearRedShoppingAmount = 0.0;
		float yearRedShoppingPercentage = 0f;
		Double yearRedFoodAmount = 0.0;
		float yearRedFoodPercentage = 0f;
		Double yearRedTravelAmount = 0.0;
		float yearRedTravelPercentage = 0f;
		Double yearRedOtherAmount = 0.0;
		float yearRedOtherPercentage = 0f;
		Double yearRedTotalAmount = 0.0;
		float yearRedTotalPercentage = 0f;
		
		if(!expenses.isEmpty()) {
			for(Expenses exp:expenses) {
				LocalDate expense_added_date = LocalDate.parse(exp.getExpense_added_date());
				int expense_added_month_value = expense_added_date.getMonthValue();
				int expense_added_year_value = expense_added_date.getYear();
				
				//blue
				if(blueYearValue == expense_added_year_value) {
					
					if(exp.getExpense_type().equals("Shopping"))
						yearBlueShoppingAmount = yearBlueShoppingAmount + exp.getExpense_amount();
					if(exp.getExpense_type().equals("Food"))
						yearBlueFoodAmount = yearBlueFoodAmount + exp.getExpense_amount();
					if(exp.getExpense_type().equals("Travel"))
						yearBlueTravelAmount = yearBlueTravelAmount + exp.getExpense_amount();
					if(exp.getExpense_type().equals("Others"))
						yearBlueOtherAmount = yearBlueOtherAmount + exp.getExpense_amount();
					yearBlueTotalAmount = yearBlueTotalAmount + exp.getExpense_amount();
					
				}
				
				//red
					if(redYearValue == expense_added_year_value) {
										
						if(exp.getExpense_type().equals("Shopping"))
							yearRedShoppingAmount = yearRedShoppingAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Food"))
							yearRedFoodAmount = yearRedFoodAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Travel"))
							yearRedTravelAmount = yearRedTravelAmount + exp.getExpense_amount();
						if(exp.getExpense_type().equals("Others"))
							yearRedOtherAmount = yearRedOtherAmount + exp.getExpense_amount();
						yearRedTotalAmount = yearRedTotalAmount + exp.getExpense_amount();
					
					
				}
				
			}
		}
		
		if(yearBlueTotalAmount>0) {
			yearBlueShoppingPercentage = Math.round((float) ((yearBlueShoppingAmount/yearBlueTotalAmount)*100));
			yearBlueFoodPercentage = Math.round((float) ((yearBlueFoodAmount/yearBlueTotalAmount)*100));
			yearBlueTravelPercentage = Math.round((float) ((yearBlueTravelAmount/yearBlueTotalAmount)*100));
			yearBlueOtherPercentage = Math.round((float) ((yearBlueOtherAmount/yearBlueTotalAmount)*100));
			yearBlueTotalPercentage = Math.round((float) ((yearBlueTotalAmount/yearBlueTotalAmount)*100));
		}
		
		if(yearRedTotalAmount>0) {
			yearRedShoppingPercentage = Math.round((float) ((yearRedShoppingAmount/yearRedTotalAmount)*100));
			yearRedFoodPercentage = Math.round((float) ((yearRedFoodAmount/yearRedTotalAmount)*100));
			yearRedTravelPercentage = Math.round((float) ((yearRedTravelAmount/yearRedTotalAmount)*100));
			yearRedOtherPercentage = Math.round((float) ((yearRedOtherAmount/yearRedTotalAmount)*100));
			yearRedTotalPercentage = Math.round((float) ((yearRedTotalAmount/yearRedTotalAmount)*100));
		}
		
		yearlyCompareDetails.put("yearBlueshoppingPercentage", (double)yearBlueShoppingPercentage);
		yearlyCompareDetails.put("yearBluefoodPercentage", (double)yearBlueFoodPercentage);
		yearlyCompareDetails.put("yearBluetravelPercentage", (double)yearBlueTravelPercentage);
		yearlyCompareDetails.put("yearBlueotherPercentage", (double)yearBlueOtherPercentage);
		yearlyCompareDetails.put("yearRedshoppingPercentage", (double)yearRedShoppingPercentage);
		yearlyCompareDetails.put("yearRedfoodPercentage", (double)yearRedFoodPercentage);
		yearlyCompareDetails.put("yearRedtravelPercentage", (double)yearRedTravelPercentage);
		yearlyCompareDetails.put("yearRedotherPercentage", (double)yearRedOtherPercentage);
		System.out.println("yearlyCompareDetails: "+yearlyCompareDetails);
		return yearlyCompareDetails;
	}
	
}
