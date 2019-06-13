using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

using Demo1.ViewModel;

namespace Demo1.Command
{
    public class CreateTaskCommand : ICommand
    {
        // There may be a warning that says this isn't used
        // But this eventhandler is necessary for implementing
        // the interface member ICommand.CanExecuteChanged
        public event EventHandler CanExecuteChanged;

        public bool CanExecute(object parameter)
        {
            return true;
        }

        public void Execute(object parameter)
        {
            // Enter Task ViewModel Items
            if (parameter is ViewModels viewmodels)
            {
                // Change Sign Values for $Amount
                int sign = 0;
                if (viewmodels.m_TaskListViewModel.TaskSign == "-")
                {
                    sign = -1;
                }
                else
                {
                    sign = 1;
                }

                // Get Category
                string categoryName = "None";
                foreach (var category in viewmodels.m_CategoryListViewModel.Category)
                {
                    if (category.m_selected == true)
                    {
                        category.m_selected = false;
                        categoryName = category.m_category;
                        break;
                    }
                }

                // Add Values to the Task List
                viewmodels.m_TaskListViewModel.Tasks.Add(new TaskViewModel()
                {
                    m_datetime = viewmodels.m_TaskListViewModel.TaskDateTime,
                    m_amount = Convert.ToDouble(viewmodels.m_TaskListViewModel.TaskAmount) * sign,
                    m_categoryName = categoryName,
                });

                // Reset Selected Values
                viewmodels.m_CategoryListViewModel.Category[0].m_selected = true;
                viewmodels.m_CategoryListViewModel.CategoryName = "Enter Category name";

            }
        }
    }
}
