package com.spring.demo.dao.expenses;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.demo.entity.Expenses;

public interface ExpensesDaoImpl extends JpaRepository<Expenses, Long>{
	
	@Query("SELECT e from Expenses e where e.username=?1 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> findByUsername(String username);
	
	@Query("SELECT e from Expenses e where e.expense_id=?1")
	List<Expenses> findByExpenseId(Long id);

	@Transactional
	@Modifying
	@Query("UPDATE Expenses e SET e.expense_type=?2, e.expense_name=?3, e.expense_amount=?4, e.expense_added_date=?5 WHERE e.expense_id=?1")
	void updateExpenseById(Long expense_id, String string, String string2, Double double1, String string3);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_type=?2 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> findByUsernameExpenseType(String username, String expense_type);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_name=?2 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseName(String username, String expense_name);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_amount=?2 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseAmount(String username, Double expense_amount);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_added_date=?2 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseDate(String username, String expense_added_date);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_type=?2 AND e.expense_name=?3 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseTypeAndExpenseName(String username, String expense_type,
			String expense_name);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_type=?2 AND e.expense_amount=?3 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseTypeAndExpenseAmount(String username, String expense_type,
			Double expense_amount);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_type=?2 AND e.expense_added_date=?3 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseTypeAndExpenseDate(String username, String expense_type,
			String expense_added_date);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_name=?2 AND e.expense_amount=?3 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseNameAndExpenseAmount(String username, String expense_name,
			Double expense_amount);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_name=?2 AND e.expense_added_date=?3 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseNameAndExpenseDate(String username, String expense_name,
			String expense_added_date);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_amount=?2 AND e.expense_added_date=?3 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseAmountAndExpenseDate(String username, Double expense_amount,
			String expense_added_date);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_type=?2 AND e.expense_name=?3 AND e.expense_amount=?4 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseAmount(String username, String expense_type,
			String expense_name, Double expense_amount);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_type=?2 AND e.expense_added_date=?3 AND e.expense_amount=?4 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseDate(String username, String expense_type,
			String expense_name, String expense_added_date);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_type=?2 AND e.expense_amount=?3 AND e.expense_added_date=?4 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseTypeAndExpenseAmountAndExpenseAmount(String username, String expense_type,
			Double expense_amount, String expense_added_date);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_name=?2 AND e.expense_amount=?3 AND e.expense_added_date=?4 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseNameAndExpenseAmountAndExpenseDate(String username, String expense_name,
			Double expense_amount, String expense_added_date);

	@Query("SELECT e from Expenses e where e.username=?1 AND e.expense_type=?2 AND e.expense_name=?3 AND e.expense_amount=?4 AND e.expense_added_date=?5 ORDER BY e.expense_added_date DESC, e.expense_id DESC")
	List<Expenses> getExpenseDetailsExpenseTypeAndExpenseNameAndExpenseAmountAndExpenseDate(String username,
			String expense_type, String expense_name, Double expense_amount, String expense_date);
	
	

}
