package com.spring.demo.service.expenses;

import java.util.HashMap;
import java.util.List;

import com.spring.demo.entity.Expenses;

public interface ExpenseServiceImpl {

	void add(Expenses expenses);

	HashMap<String, String> getMonthlyAnalysisDetails(String username, String month_monthly_analysis, String year_monthly_analysis, String year_yearly_analysis);

	List<Expenses> getExpenseDetails(String string, String expense_type, String expense_name, Double expense_amount, String expense_date);

	void deleteExpense(Long expense_id);

	Expenses getExpenseDetail(Long id);

	void updateExpense(Expenses expense);

	List<Expenses> getExpenseDetails(String username);

	HashMap<String, Double> getCompareMonthlyExpense(String month_compare_blue_month_data,
			String month_compare_blue_year_data, String month_compare_red_month_data,
			String month_compare_red_year_data, String month_compare_red_year_data2);

	HashMap<String, Double> getCompareYearlyExpense(String username, String blueYearlyYearValue,
			String redYearlyYearValue);

}
