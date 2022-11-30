package com.spring.demo.dao.expenses;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.spring.demo.entity.Expenses;

@Repository
public class ExpensesDao {
	@Autowired
	private ExpensesDaoImpl expensesDaoImpl;

	public void addExpense(Expenses expenses) {
		expensesDaoImpl.save(expenses);
	}

	public List<Expenses> getMonthlyAnalysisDetails(String username) {
		List<Expenses> expenses = expensesDaoImpl.findByUsername(username);
		return expenses;
	}

	public List<Expenses> getExpenseDetails(String username) {
		List<Expenses> expenses = expensesDaoImpl.findByUsername(username);
		return expenses;
	}

	public void deleteExpense(Long expense_id) {
		expensesDaoImpl.deleteById(expense_id);
	}

	public Expenses getExpenseDetails(Long id) {
		List<Expenses> exps = expensesDaoImpl.findByExpenseId(id);
		Expenses expense = new Expenses();
		for(Expenses exp:exps) {
			expense = exp;
		}
		return expense;
	}

	public void updateExpense(Expenses expense) {
		expensesDaoImpl.updateExpenseById(expense.getExpense_id(), expense.getExpense_type(), expense.getExpense_name(), expense.getExpense_amount(), expense.getExpense_added_date());
	}

	public List<Expenses> getExpenseDetailsExpenseType(String username, String expense_type) {
		System.out.println("Inside getExpenseDetailsExpenseType");
		List<Expenses> expenses = expensesDaoImpl.findByUsernameExpenseType(username, expense_type);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseName(String username, String expense_name) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseName(username, expense_name);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseAmount(String username, Double expense_amount) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseAmount(username, expense_amount);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseDate(String username, String expense_date) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseDate(username, expense_date);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseTypeAndExpenseName(String username, String expense_type,
			String expense_name) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseTypeAndExpenseName(username, expense_type, expense_name);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseTypeAndExpenseAmount(String username, String expense_type,
			Double expense_amount) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseTypeAndExpenseAmount(username, expense_type, expense_amount);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseTypeAndExpenseDate(String username, String expense_type,
			String expense_date) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseTypeAndExpenseDate(username, expense_type, expense_date);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseNameAndExpenseAmount(String username, String expense_name,
			Double expense_amount) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseNameAndExpenseAmount(username, expense_name, expense_amount);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseNameAndExpenseDate(String username, String expense_name,
			String expense_date) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseNameAndExpenseDate(username, expense_name, expense_date);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseAmountAndExpenseDate(String username, Double expense_amount,
			String expense_date) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseAmountAndExpenseDate(username, expense_amount, expense_date);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseAmount(String username,
			String expense_type, String expense_name, Double expense_amount) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseAmount(username,
				expense_type, expense_name, expense_amount);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseDate(String username, String expense_type,
			String expense_name, String expense_date) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseDate(username,
				expense_type, expense_name, expense_date);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseTypeAndExpenseAmountAndExpenseAmount(String username,
			String expense_type, Double expense_amount, String expense_date) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseTypeAndExpenseAmountAndExpenseAmount(username,
				expense_type, expense_amount, expense_date);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseNameAndExpenseAmountAndExpenseDate(String username,
			String expense_name, Double expense_amount, String expense_date) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseNameAndExpenseAmountAndExpenseDate(username,
				expense_name, expense_amount, expense_date);
		return expenses;
	}

	public List<Expenses> getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseAmountAndExpenseDate(String username,
			String expense_type, String expense_name, Double expense_amount, String expense_date) {
		List<Expenses> expenses = expensesDaoImpl.getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseAmountAndExpenseDate(username,
				expense_type, expense_name, expense_amount, expense_date);
		return expenses;
	}
}
