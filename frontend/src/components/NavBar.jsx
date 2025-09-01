import { Search } from "lucide-react";
import { Field, Form, Formik } from "formik";
import * as Yup from "yup";

function NavBar() {
  const searchOptions = ["Hospital", "Doctor"];
  return (
    <nav className="w-screen px-[5%] py-[10px] h-[65px] flex justify-between bg-white shadow-lg shadow-black/5 fixed top-0">
      <div className="h-full flex items-center">
        <h1 className="text-3xl text-gray-800 font-bold font-agbalumo flex items-center">
          MediAssist
        </h1>
      </div>
      <div className="w-[30%] h-full py-5px">
        <SearchBarDropdown options={searchOptions} />
      </div>
      <div className="w-[38%] h-full flex justify-between items-center">
        <ul className="flex gap-6 text-sm items-center font-semibold text-gray-800">
          <li className="hover:text-gray-800/60 cursor-pointer">
            <a href="#">Home</a>
          </li>
          <li className="hover:text-gray-800/60 cursor-pointer">
            <a href="#">About Us</a>
          </li>
          <li className="hover:text-gray-800/60 cursor-pointer">
            <a href="#">Book an Appointment</a>
          </li>
        </ul>
        <button
          type="button"
          className="h-[90%] px-10 cursor-pointer rounded-lg bg-blue-800 font-semibold text-gray-100 text-sm
          hover:bg-blue-800/85 transition-all duration-200"
        >
          Login
        </button>
      </div>
    </nav>
  );
}

function SearchBarDropdown({ options }) {
  const initialValues = {
    category: options[0],
    searchKey: "",
  };

  const validationSchema = Yup.object({
    searchKey: Yup.string().required(),
  });
  const onSubmit = (values) => {
    console.log(values);
  };
  return (
    <Formik {...{ initialValues, onSubmit, validationSchema }}>
      <div className="h-full w-full flex items-center">
        <Form className="w-full flex  border-gray-800 h-[90%] rounded-[300px] overflow-hidden relative">
          <Field
            name="category"
            component="select"
            className="w-[40%] h-full focus:outline-none pl-[12px] bg-blue-800 text-sm text-gray-100
        hover:bg-blue-800/85 transition-all duration-200
        "
          >
            {options.map((e, idx) => (
              <option key={idx} value={e}>
                {e}
              </option>
            ))}
          </Field>
          <Field
            name="searchKey"
            type="text"
            placeholder="Search"
            className="h-full w-full border-l-[1px] focus:outline-0 px-[10px] text-sm bg-gray-200 text-gray-900
          focus:bg-gray-200/70 hover:bg-gray-200/70 placeholder:text-gray-900/80 transition-all duration-200"
          />
          <button
            type="submit"
            className="absolute right-[6px] top-[50%] translate-[-50%] cursor-pointer"
          >
            <Search size={18} />
          </button>
        </Form>
      </div>
    </Formik>
  );
}

export default NavBar;
