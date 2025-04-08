CREATE DATABASE IF NOT EXISTS `finance_tracker`;
USE `finance_tracker`;

-- Drop table if it exists
DROP TABLE IF EXISTS `transactions`;

-- Create the transactions table
CREATE TABLE `transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) DEFAULT NULL,
  `amount` DECIMAL(10,2) DEFAULT NULL,
  `date` DATE DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Insert data into the transactions table
INSERT INTO `transactions` (`id`, `type`, `amount`, `date`, `description`) VALUES 
  (1, 'Expense', 89.99, '2024-04-01', 'Grocery'),
  (2, 'Income', 1000.00, '2024-04-01', 'Job Salary'),
  (3, 'Expense', 54.99, '2024-04-02', 'Internet'),
  (4, 'Expense', 34.49, '2024-04-03', 'Gas'),
  (5, 'Income', 500.00, '2024-04-04', 'Side Business');