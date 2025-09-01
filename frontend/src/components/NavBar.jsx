import { Search } from "lucide-react";

function NavBar() {
  const searchOptions = ["Hospital", "Doctor"];
  return (
    <nav className="w-screen px-[5%] py-[10px] h-[60px] flex justify-between bg-white shadow-lg">
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
          className="h-[90%] px-10 cursor-pointer rounded-lg bg-gray-800 font-semibold text-gray-100 text-sm
          hover:bg-gray-800/92 transition-all duration-200"
        >
          Login
        </button>
      </div>
    </nav>
  );
}

function SearchBarDropdown({ options = [] }) {
  return (
    <div className="h-full w-full flex items-center">
      <form className="w-full flex  border-gray-800 h-[90%] rounded-lg overflow-hidden relative">
        <select className="w-[40%] h-full focus:outline-0 pl-[6px] bg-gray-800 text-sm text-gray-100">
          {options.map((e) => (
            <option>{e}</option>
          ))}
        </select>
        <input
          type="text"
          placeholder="Search"
          className="h-full w-full border-l-[1px] focus:outline-0 px-[10px] text-sm bg-gray-300 text-gray-900 
          focus:bg-gray-200 hover:bg-gray-200 placeholder:text-gray-900/80 transition-all duration-200"
        />
        <button
          type="submit"
          className="absolute right-[2px] top-[50%] translate-[-50%] cursor-pointer"
        >
          <Search size={18} />
        </button>
      </form>
    </div>
  );
}

export default NavBar;
