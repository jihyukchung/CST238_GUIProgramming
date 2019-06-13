using Demo1.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace Demo1.Command
{
    public class DeleteCategoryCommand : ICommand
    {
        public event EventHandler CanExecuteChanged;

        public bool CanExecute(object parameter)
        {
            return true;
        }

        public void Execute(object parameter)
        {
            if (parameter is ViewModels viewmodels)
            {
                // Get Name and Location of the Selected
                string selectedCategoryName = "";
                int selectedCategoryLoc = 0;
                foreach (var category in viewmodels.m_CategoryListViewModel.Category)
                {
                    if (category.m_selected == true)
                    {
                        category.m_selected = false;
                        selectedCategoryName = category.m_category;
                        break;
                    }

                    selectedCategoryLoc++;
                }

                if (selectedCategoryName != "")
                {
                    // Remove Tasks of Selected Category
                    List<int> selectedTask = new List<int>();
                    int counter = 0;
                    foreach (var tasks in viewmodels.m_TaskListViewModel.Tasks)
                    {
                        if (tasks.m_categoryName == selectedCategoryName)
                        {
                            selectedTask.Add(counter);
                        }

                        counter++;
                    }

                    // Remove Category Selected
                    // But Do Not Remove Default Category, Only the Tasks 
                    selectedTask.Reverse();
                    foreach (var x in selectedTask)
                    {
                        viewmodels.m_TaskListViewModel.Tasks.RemoveAt(x);
                    }
                    if (selectedCategoryLoc != 0)
                    {
                        viewmodels.m_CategoryListViewModel.Category.RemoveAt(selectedCategoryLoc);
                    }
                }
            }
        }
    }
}
