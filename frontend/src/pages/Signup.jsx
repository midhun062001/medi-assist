import React, { useState } from "react";

export default function Signup() {
  //TODO: validate form data
  const genders = ["Male", "Female", "Others"];
  const bloodGroups = [
    { key: "A_POSITIVE", value: "A+" },
    { key: "A_NEGATIVE", value: "A-" },
    { key: "B_POSITIVE", value: "B+" },
    { key: "B_NEGATIVE", value: "B-" },
    { key: "AB_POSITIVE", value: "AB+" },
    { key: "AB_NEGATIVE", value: "AB-" },
    { key: "O_POSITIVE", value: "O+" },
    { key: "O_NEGATIVE", value: "O-" },
  ];
  const countryCodes = [
    { key: "IN", value: "India" },
    { key: "US", value: "United States" },
    { key: "GB", value: "United Kingdom" },
    { key: "CA", value: "Canada" },
    { key: "AU", value: "Australia" },
    { key: "SG", value: "Singapore" },
    { key: "AE", value: "United Arab Emirates" },
    { key: "DE", value: "Germany" },
    { key: "FR", value: "France" },
    { key: "JP", value: "Japan" },
    { key: "NZ", value: "New Zealand" },
  ];

  // State
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [dob, setDob] = useState(new Date().toISOString().split("T")[0]);
  const [gender, setGender] = useState(genders[0]);
  const [bloodGroup, setBloodGroup] = useState(bloodGroups[0].key);
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [state, setState] = useState("");
  const [district, setDistrict] = useState("");
  const [zip, setZip] = useState("");
  const [nationality, setNationality] = useState(countryCodes[0].key);
  const [aadhar, setAadhar] = useState("");
  const [passport, setPassport] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  // Reusable Text Input
  const TextInput = ({
    id,
    value,
    setterValue,
    label,
    type = "text",
    placeholder,
    span = 2,
  }) => (
    <div className={`col-span-${span} flex flex-col`}>
      <label htmlFor={id} className="text-[12px] font-bold text-black-100">
        {label}
      </label>
      <input
        id={id}
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={(e) => setterValue(e.target.value)}
        className="border-2 border-gray-400 px-1 h-[32px] text-[16px] text-black-100 rounded-md hover:border-gray-700 placeholder:text-gray-500 transition-all duration-100"
      />
    </div>
  );

  // Reusable Select Input
  const SelectInput = ({
    id,
    value,
    setterValue,
    label,
    options,
    span = 2,
  }) => (
    <div className={`col-span-${span} flex flex-col`}>
      <label htmlFor={id} className="text-[12px] font-bold text-black-100">
        {label}
      </label>
      <select
        id={id}
        value={value}
        onChange={(e) => setterValue(e.target.value)}
        className="border-2 border-gray-400 px-1 h-[32px] text-[16px] text-black-100 rounded-md hover:border-gray-700 transition-all ease-in duration-100"
      >
        {options.map((opt, idx) =>
          typeof opt === "string" ? (
            <option key={idx} value={opt}>
              {opt}
            </option>
          ) : (
            <option key={idx} value={opt.key}>
              {opt.value}
            </option>
          )
        )}
      </select>
    </div>
  );

  // Submit handler
  const handleSubmit = (e) => {
    e.preventDefault();
    const payload = {
      firstName,
      lastName,
      dob,
      gender: gender.toUpperCase(),
      bloodGroup,
      email,
      phone,
      state,
      district,
      zip,
      nationality,
      aadhar,
      passport,
      password,
    };
    console.log("Form Data:", payload);

    //TODO: transfer the payload to the backend API
    //TODO:: upon successful registration, route to login page
  };

  return (
    <div className="h-screen w-screen flex items-center justify-center py-10 px-20 bg-white-300">
      <div className="h-full w-full grid grid-cols-6 rounded-lg overflow-hidden">
        {/* Left panel */}
        <div className="h-full w-full bg-blue-200 col-span-2 flex flex-col items-center justify-center px-10 py-8">
          <h1 className="text-3xl font-bold text-center w-full text-blue-800">
            Welcome
          </h1>
          <div className="flex items-center justify-center h-full w-full">
            <img src="src/assets/rocket.svg" className="w-[70%]" alt="Rocket" />
          </div>
        </div>

        {/* Right panel */}
        <div className="h-full w-full bg-white col-span-4 px-10 py-8 flex min-h-0">
          <div className="h-full w-full flex min-h-0">
            <div className="h-full w-full overflow-y-auto p-4 rounded-md">
              <form
                onSubmit={handleSubmit}
                className="w-full grid grid-cols-6 gap-x-2 gap-y-4"
              >
                <TextInput
                  id="first_name"
                  value={firstName}
                  setterValue={setFirstName}
                  label="First Name"
                  placeholder="First Name"
                  span={3}
                />
                <TextInput
                  id="last_name"
                  value={lastName}
                  setterValue={setLastName}
                  label="Last Name"
                  placeholder="Last Name"
                  span={3}
                />
                <TextInput
                  id="dob"
                  value={dob}
                  setterValue={setDob}
                  label="Date of Birth"
                  type="date"
                  span={2}
                />
                <SelectInput
                  id="gender"
                  label="Gender"
                  value={gender}
                  setterValue={setGender}
                  options={genders}
                  span={2}
                />
                <SelectInput
                  id="blood_group"
                  label="Blood Group"
                  value={bloodGroup}
                  setterValue={setBloodGroup}
                  options={bloodGroups}
                  span={2}
                />
                <TextInput
                  id="email"
                  value={email}
                  setterValue={setEmail}
                  label="Email"
                  type="email"
                  placeholder="example@email.com"
                  span={3}
                />
                <TextInput
                  id="phone"
                  value={phone}
                  setterValue={setPhone}
                  label="Mobile Number"
                  type="tel"
                  placeholder="Mobile Number"
                  span={3}
                />
                <TextInput
                  id="state"
                  value={state}
                  setterValue={setState}
                  label="State"
                  placeholder="State"
                />
                <TextInput
                  id="district"
                  value={district}
                  setterValue={setDistrict}
                  label="District"
                  placeholder="District"
                />
                <TextInput
                  id="zip"
                  value={zip}
                  setterValue={setZip}
                  label="Zip Code"
                  placeholder="Zip / Pin Code"
                />
                <SelectInput
                  id="nationality"
                  label="Nationality"
                  value={nationality}
                  setterValue={setNationality}
                  options={countryCodes}
                />
                <TextInput
                  id="aadhar"
                  value={aadhar}
                  setterValue={setAadhar}
                  label="Aadhar Number"
                  placeholder="Aadhar Number"
                />
                <TextInput
                  id="passport"
                  value={passport}
                  setterValue={setPassport}
                  label="Passport"
                  placeholder="Passport Number"
                />
                <TextInput
                  id="password_original"
                  value={password}
                  setterValue={setPassword}
                  label="Password"
                  type="password"
                  placeholder="Password"
                  span={3}
                />
                <TextInput
                  id="password_confirm"
                  value={confirmPassword}
                  setterValue={setConfirmPassword}
                  label="Confirm Password"
                  type="password"
                  placeholder="Confirm Password"
                  span={3}
                />

                {/* Submit */}
                <div className="col-span-6 flex items-center justify-center pt-5">
                  <button
                    type="submit"
                    className="bg-blue-600 hover:bg-blue-700 h-[40px] px-8 py-2 text-white text-[16px] font-semibold rounded-md shadow-md transition duration-200"
                  >
                    Submit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
