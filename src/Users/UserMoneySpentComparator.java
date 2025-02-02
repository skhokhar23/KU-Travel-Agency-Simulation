package Users;

import java.util.Comparator;


	public class UserMoneySpentComparator implements Comparator<User> {
	    @Override
	    public int compare(User user1, User user2) {
	        // Compare total money spent in descending order
	        return Double.compare(user1.getTotalMoneySpent(), user2.getTotalMoneySpent());
	    
	}
	}
