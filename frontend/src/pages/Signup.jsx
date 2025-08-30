import { Formik, Form, ErrorMessage, Field } from "formik";
import * as Yup from "yup";

export default function Signup() {
  //TODO: validate form data
  const genders = [
    { key: "MALE", value: "Male" },
    { key: "FEMALE", value: "Female" },
    { key: "OTHERS", value: "Others" },
  ];

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

  const user = {
    firstName: "",
    lastName: "",
    dob: "",
    gender: genders[0],
    bloodGroup: bloodGroups[0].value,
    email: "",
    phone: "",
    district: "",
    state: "",
    zip: "",
    nationality: countryCodes[0].value,
    aadhar: "",
    passport: "",
    password: "",
    confirmPassword: "",
  };

  const validationSchema = Yup.object({
    firstName: Yup.string()
      .required("Required!")
      .matches(
        /^[A-Za-z\s]+$/,
        "first name can contain only alphabets and spaces"
      )
      .min(2, "first name must be at least 2 characters"),
    lastName: Yup.string()
      .required("Required!")
      .matches(
        /^[A-Za-z\s]+$/,
        "last name can contain only alphabets and spaces"
      )
      .min(2, "last name must be at least 2 characters"),
    dob: Yup.date()
      .required("DOB required")
      .max(new Date(), "DOB cannot be in future"),
    email: Yup.string().email("Invalid email").required("Email Required!"),
    phone: Yup.string()
      .matches(/^[0-9]{10}$/, "phone number must have 10 digits")
      .required("Phone Number Required!"),
  });

  const onSubmit = (values) => console.log(values);

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
            <div className="h-full w-full overflow-y-auto p-4 rounded-md ">
              <Formik
                initialValues={user}
                onSubmit={onSubmit}
                validationSchema={validationSchema}
              >
                <Form className="grid grid-cols-6 gap-x-2 gap-y-1">
                  <TextInput
                    type="text"
                    name="firstName"
                    placeHolder="Enter your first name"
                    label="First Name"
                    title="Enter your first name"
                    span={3}
                  />
                  <TextInput
                    type="text"
                    name="lastName"
                    placeHolder="Enter your last name"
                    label="Last Name"
                    title="Enter your last name"
                    span={3}
                  />
                  <TextInput
                    type="date"
                    name="dob"
                    label="Date of Birth"
                    title="Enter your date of birth"
                    span={2}
                  />
                  <SelectInput
                    name="gender"
                    span={2}
                    label="Gender"
                    title="Select your gender"
                    options={genders}
                  />
                  <SelectInput
                    name="bloodGroup"
                    span={2}
                    label="Blood Group"
                    title="Select your blood group"
                    options={bloodGroups}
                  />
                  <TextInput
                    type="email"
                    name="email"
                    label="Email"
                    placeHolder="Enter your email address"
                    title="Enter your email address"
                    span={3}
                  />
                  <TextInput
                    type="tel"
                    name="phone"
                    label="Phone"
                    placeHolder="Enter your phone number"
                    title="Enter your phone number"
                    span={3}
                  />
                  <TextInput
                    name="state"
                    label="State"
                    placeHolder="Enter your state"
                    title="Enter your state"
                  />
                  <TextInput
                    name="district"
                    label="District"
                    placeHolder="Enter your district"
                    title="Enter your district"
                  />
                  <TextInput
                    name="zip"
                    label="Zip / Pin Code"
                    placeHolder="Enter your zip / pin code"
                    title="Enter your zip/pin code"
                  />
                  <SelectInput
                    name="nationality"
                    label="Nationality"
                    title="Select your Nationality"
                    options={countryCodes}
                  />
                  <TextInput
                    name="aadhar"
                    label="Aadhar Number"
                    placeHolder="Enter your aadhar number"
                    title="Enter your aadhar number"
                  />
                  <TextInput
                    name="passport"
                    label="Passport Number"
                    placeHolder="Enter your passport number"
                    title="Enter your passport number"
                  />
                  <div className="col-span-6 py-5 mb-4 bg-gray-300 rounded-md px-4">
                    <h3 className="text-sm font-semibold text-gray-700 mb-2">
                      Password must contain:
                    </h3>
                    <ul className="list-disc list-inside text-sm text-gray-600 space-y-1">
                      <li>At least 8 characters</li>
                      <li>At least one uppercase letter (A–Z)</li>
                      <li>At least one lowercase letter (a–z)</li>
                      <li>At least one number (0–9)</li>
                      <li>At least one special character (!@#$%^&*)</li>
                    </ul>
                  </div>

                  <TextInput
                    name="password"
                    label="Password"
                    placeHolder="Enter your password"
                    title="Enter your password"
                    span={3}
                  />
                  <TextInput
                    name="confirmPassword"
                    label="Confirm Password"
                    placeHolder="Confirm your password"
                    title="confirm your password"
                    span={3}
                  />
                  <div className="col-span-6 flex justify-center">
                    <button
                      className="bg-blue-800 px-15 py-2 rounded-md text-white-200 font-semibold text-sm hover:bg-blue-700 transition-all duration-100"
                      type="submit"
                    >
                      Register
                    </button>
                  </div>
                </Form>
              </Formik>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

const spanClasses = {
  1: "col-span-1",
  2: "col-span-2",
  3: "col-span-3",
  4: "col-span-4",
};

const TextInput = ({
  type = "text",
  name = "",
  placeHolder = "",
  span = 2,
  label = "",
  title = "",
}) => {
  return (
    <div className={`${spanClasses[span]} flex flex-col gap-0.5 h-[80px] `}>
      <label className="text-sm text-gray-700 font-bold">{label}</label>
      <Field
        type={type}
        name={name}
        title={title}
        placeholder={placeHolder}
        className="h-[36px] text-[16px] px-1.5 border-2 border-gray-500 rounded-md placeholder:text-gray-400 focus:border-gray-900 hover:border-gray-900 transition-all duration-100"
      />
      <ErrorMessage
        name={name}
        component="div"
        className="text-red-600 text-xs"
      />
    </div>
  );
};

const SelectInput = ({
  name = "",
  span = 2,
  label = "",
  title = "",
  options = [],
}) => {
  return (
    <div className={`${spanClasses[span]} flex flex-col gap-0.5 h-[80px] `}>
      <label className="text-sm text-gray-700 font-bold">{label}</label>
      <Field
        component="select"
        name={name}
        title={title}
        className="h-[36px] text-[16px] px-1.5 border-2 border-gray-500 rounded-md placeholder:text-gray-400 focus:border-gray-900 hover:border-gray-900 transition-all duration-100"
      >
        {options.map((a) => (
          <option key={a.key} value={a.key}>
            {a.value}
          </option>
        ))}
      </Field>
      <ErrorMessage
        name={name}
        component="div"
        className="text-red-600 text-xs"
      />
    </div>
  );
};
