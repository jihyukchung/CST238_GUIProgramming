using Demo1.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace Demo1.Command
{
    public class CreateCategoryCommand : ICommand
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
            // Enter Category ViewModel Itewms
            if (parameter is CategoryListViewModel categoryList)
            {
                bool doesExist = false;
                foreach (var category in categoryList.Category)
                {
                    if (categoryList.CategoryName == category.m_category)
                    {
                        doesExist = true;
                        break;
                    }
                }

                if (doesExist == false)
                {
                    categoryList.Category.Add(new CategoryViewModel()
                    {
                        m_category = categoryList.CategoryName,
                        m_id = categoryList.Size,
                        m_selected = categoryList.IsSelected,
                    });

                    categoryList.Size++;
                }
            }
        }
    }
}
