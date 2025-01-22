import {useCallback, useState} from "react";

export const useForm = <T>(init: T) => {
  const [form, setForm] = useState<T>(init);

  const setValue = useCallback(<K extends keyof T>(input: K, value: T[K]) =>
    setForm({...form, [input]: value}), [form])

  const setValueName = useCallback(
    <K extends keyof T>(input: K) => (value: T[K]) => setValue(input, value),
    [form])

  return {
    setValue,
    setValueName,
    form
  }
}