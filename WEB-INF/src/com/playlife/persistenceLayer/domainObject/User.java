package com.playlife.persistenceLayer.domainObject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

@Entity(name = "User")
@Table(name = "User")
@NamedQueries({
    @NamedQuery(name="User.hql_find_ByEmail",
                query="from User u where u.email = :email"),
    @NamedQuery(name="User.hql_find_ByEmailAndPassword",
                query="from User u where u.email = :email AND u.password = :password"),
}) 
public class User implements Serializable{
	/****************
	 * 				*
	 * 	 Constant	*
	 * 				*
	 ****************/
	private static final long serialVersionUID = 1L;
	private static String DEFAULT_USERNAME = "-----";
	
	/********************
	 * 					*
	 * 	 Constructor	*
	 * 					*
	 ********************/
	public User(String email, String password, String username, Locale language, User_Role role) {
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
		this.setLanguage(language);
		this.setDisabled(false);
		this.setRole(role);
		this.setBookSets(new HashSet<BookSet>());
		this.setBookSupports(new HashSet<BookSupport>());
		this.setPlaceRatings(new HashSet<PlaceRating>());
		this.setReports(new HashSet<Report>());
		this.setBooks(new HashSet<Book>());
	}
	
	public User(String email, String password, String username, Locale language) {
		this(email, password, username, language, User_Role.USER);
	}
	public User(String email, String password, String username){
		this(email, password, username, Locale.getDefault());
	}
	public User(String email, String password) {
		this(DEFAULT_USERNAME, email, password, Locale.getDefault());
	}
	public User() {
		this(DEFAULT_USERNAME, null, null, Locale.getDefault());
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<BookSupport> getBookSupports() {
		return bookSupports;
	}
	public void setBookSupports(Set<BookSupport> bookSupports) {
		this.bookSupports = bookSupports;
	}
	public void addBookSupport(BookSupport bookSupport) {
		this.getBookSupports().add(bookSupport);
	}
	public Set<PlaceRating> getPlaceRatings() {
		return placeRatings;
	}
	public void setPlaceRatings(Set<PlaceRating> placeRatings) {
		this.placeRatings = placeRatings;
	}
	public void addPlaceRating(PlaceRating placeRating) {
		this.getPlaceRatings().add(placeRating);
	}
	public Set<Report> getReports() {
		return reports;
	}
	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	public void addReport(Report report) {
		this.getReports().add(report);
	}
	public Set<BookSet> getBookSets() {
		return bookSets;
	}
	public void setBookSets(Set<BookSet> bookSets) {
		this.bookSets = bookSets;
	}
	public void addBookSet(BookSet bookSet) {
		this.getBookSets().add(bookSet);
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public Locale getLanguage() {
		return language;
	}
	public void setLanguage(Locale language) {
		this.language = language;
	}
	public boolean isDisabled() {
		return isDisabled;
	}
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	public User_Role getRole() {
		return role;
	}
	public void setRole(User_Role role) {
		this.role = role;
	}


	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", nullable=false, unique=true)
	public Long userId;
	
	@Column(name = "username")
	public String username;
	
	@Column(name = "email", nullable=false, unique=true)
	public String email;
	
	@Column(name = "password", nullable=false)
	public String password;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<BookSupport> bookSupports;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<PlaceRating> placeRatings;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<BookSet> bookSets;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<Book> books;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<Report> reports;
	
	@Column(name="language") 
	private Locale language;
	
	@Column(name = "isDisabled")
	@Type(type="true_false")
	private boolean isDisabled;
	
	@Column(name="role") 
	@Enumerated(EnumType.STRING) 
	private User_Role role;
}
