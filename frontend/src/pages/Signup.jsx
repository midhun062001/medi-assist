import { Formik, Form, ErrorMessage, Field } from "formik";
import * as Yup from "yup";

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

  const user = {
    firstName: "",
    lastName: "",
    dob: new Date().toISOString().split("T")[0],
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
                    placeHolder="Enter your First name"
                    label="First Name"
                    title="Enter your first name"
                    span={3}
                  />
                  <TextInput
                    type="text"
                    name="lastName"
                    placeHolder="Enter your Last name"
                    label="Last Name"
                    title="Enter your last name"
                    span={3}
                  />
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
        className="h-[36px] text-[16px] px-1.5 border-2 border-gray-500 rounded-md placeholder:text-gray-400"
      />
      <ErrorMessage
        name={name}
        component="div"
        className="text-red-600 text-xs"
      />
    </div>
  );
};
