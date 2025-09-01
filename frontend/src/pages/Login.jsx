import { ErrorMessage, Field, Form, Formik } from "formik";
import { Eye, EyeOff, Lock, Mail, UserRound } from "lucide-react";
import { useState } from "react";
import * as Yup from "yup";

function Login() {
  const initialValues = {
    userName: "",
    password: "",
  };

  const emailRegex =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  const phoneRegex = /^[0-9]{10}$/;

  const validationSchema = Yup.object({
    userName: Yup.string()
      .label("user name")
      .required()
      .test(
        "test email or phone",
        "User name must either phone or email",
        (value) => {
          if (!value) return false;
          return emailRegex.test(value) || phoneRegex.test(value);
        }
      ),
    password: Yup.string().required(),
  });

  const onSubmit = (values) => {
    console.log(values);
  };
  return (
    <div className="h-[100dvh] w-[100dvw] bg-gray-100  flex items-center justify-center">
      <div className="w-[70%] h-[80%] px-15 py-10">
        <div className=" h-full w-full flex gap-5 rounded-lg overflow-hidden bg-blue-100 shadow-xl">
          <div className="w-[60%] h-full flex items-center justify-center">
            <img src="src\assets\doctor.svg" className="h-[60%]" />
          </div>
          <div className="h-full w-[40%]  bg-blue-700 flex items-center justify-center">
            <div className="h-[88%] w-[80%]  flex flex-col items-center">
              <div className="h-[100px] w-[100px] mb-5 rounded-[100%] overflow-hidden bg-blue-800 flex items-center justify-center">
                <UserRound size={45} color="#dbeafe " strokeWidth={1} />
              </div>
              <div className="w-full h-[calc(100%-110px)] overflow-y-auto px-[5px] py-[10px]">
                <Formik {...{ initialValues, validationSchema, onSubmit }}>
                  <Form className=" flex flex-col gap-4 ">
                    <TextInput name="userName" />
                    <PasswordInput name="password" />
                    <div className="w-full pb-[16px] flex justify-between ">
                      <a
                        href="#"
                        className="text-[0.8rem] text-blue-50 hover:text-blue-50/80 transition-all duration-200"
                      >
                        Forget password ?
                      </a>
                      <a
                        href="#"
                        className="text-[0.8rem] text-blue-50 hover:text-blue-50/80 transition-all duration-200"
                      >
                        Create Account
                      </a>
                    </div>
                    <div className="w-full flex justify-center">
                      <button
                        type="submit"
                        className="px-7 py-1.5 bg-blue-100 font-semibold cursor-pointer text-[0.8rem] rounded-lg text-blue-700 
                        hover:bg-blue-100/90 transition-all duration-200"
                      >
                        Login
                      </button>
                    </div>
                  </Form>
                </Formik>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

function TextInput({ name = "" }) {
  return (
    <div className="w-full h-[60px]">
      <div className="relative mb-[2px] overflow-hidden">
        <Mail
          size={18}
          className="text-blue-200 absolute top-[50%] left-[4px] translate-y-[-50%]"
          strokeWidth={1}
        />
        <Field
          name={name}
          className="w-full pl-[28px] py-2 border-[1px] border-transparent border-b-blue-100/70 text-[0.9rem] text-blue-50
        focus:outline-0 focus:border-blue-100/70 focus:rounded-lg bg-transparent
        [--autofill-fg:theme(colors.blue.50)] transition-all duration-200"
          placeholder="Enter phone / email"
        />
      </div>

      <ErrorMessage
        name={name}
        component="div"
        className="text-red-100 text-[12px]"
      />
    </div>
  );
}

function PasswordInput({ name = "" }) {
  const [showPassword, setShowPassword] = useState(false);
  return (
    <div className="w-full h-[55px]">
      <div className="relative mb-[2px] overflow-hidden">
        <Lock
          size={18}
          className="text-blue-200 absolute top-[50%] left-[4px] translate-y-[-50%]"
          strokeWidth={1}
        />
        <Field
          name={name}
          className="w-full pl-[28px] py-2 border-[1px] border-transparent border-b-blue-100/70 text-[0.9rem] text-blue-50
        focus:outline-0 focus:border-blue-100/70 focus:rounded-lg
        [--autofill-fg:theme(colors.blue.50)] transition-all duration-200"
          placeholder="Enter password"
          type={`${showPassword ? "text" : "password"}`}
        />
        <button
          className="absolute right-[4px] top-[50%] translate-y-[-50%]"
          type="button"
          onMouseDown={() => setShowPassword(true)}
          onMouseUp={() => setShowPassword(false)}
        >
          {showPassword ? (
            <Eye size={18} className="text-blue-200" strokeWidth={1} />
          ) : (
            <EyeOff size={18} className="text-blue-200 " strokeWidth={1} />
          )}
        </button>
      </div>
      <ErrorMessage
        name={name}
        component="div"
        className="text-red-100 text-[12px]"
      />
    </div>
  );
}

export default Login;
